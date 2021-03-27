package com.phongvo.estatespringboot.repository.custom.impl;

import com.phongvo.estatespringboot.builder.BuildingBuilder;
import com.phongvo.estatespringboot.entity.Building;
import com.phongvo.estatespringboot.paging.PageRequest;
import com.phongvo.estatespringboot.repository.custom.BuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Building> findAll(BuildingBuilder builder, PageRequest pageRequest) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT b.* FROM building as b ");
        sql.append("WHERE TRUE ");
        sql = buildSqlBuildingSpecial(builder, sql);
        sql = buildSqlBuildingCommon(builder, sql);
        sql.append(" ORDER BY modifieddate DESC ");
//        if(pageRequest.getOffset() != null && pageRequest.getLimit() != null){
//            sql.append(" LIMIT "+pageRequest.getOffset() +","+pageRequest.getLimit()+" ");
//        }
        Query query = entityManager.createNativeQuery(sql.toString(), Building.class);
        return query.getResultList();
    }

    private StringBuilder buildSqlBuildingCommon(BuildingBuilder builder, StringBuilder sql) {
        // Staff
        if(builder.getStaffId() != null){
            sql.append(" AND EXISTS(SELECT * FROM assignment_building as asb " +
                    "WHERE asb.building_id = b.id AND asb.user_id = "+builder.getStaffId()+") ");
        }
        //Area
        if(builder.getAreaFrom() != null || builder.getAreaTo() != null){
            sql.append(" AND EXISTS ( SELECT * FROM rent_area as ra where ra.building_id = b.id ");
            if(builder.getAreaFrom() != null){
                sql.append(" AND ra.value >= " + builder.getAreaFrom() + " ");
            }
            if(builder.getAreaTo() != null){
                sql.append(" AND ra.value <= " + builder.getAreaTo() + " ");
            }
            sql.append(")");
        }
        // RentCost
        if(builder.getCostFrom() != null || builder.getCostTo() != null){
            if(builder.getCostFrom() != null){
                sql.append(" AND b.rentcost >= " + builder.getCostFrom());
            }
            if(builder.getCostTo() != null){
                sql.append(" AND b.rentcost <= " + builder.getCostTo());
            }
        }
        //Building Type
        if(builder.getTypes() != null && builder.getTypes().length != 0){
            sql.append(" AND ( ");
            String sqlTypes = Arrays.stream(builder.getTypes())
                    .map(item -> "b.type like '%"+ item +"%'")
                    .collect(Collectors.joining(" OR "));
            sql.append(sqlTypes);
            sql.append(" )");
        }
        return sql;
    }

    private StringBuilder buildSqlBuildingSpecial(BuildingBuilder builder, StringBuilder sql) {
        try{
            Field[] fields = BuildingBuilder.class.getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                if(field.get(builder) != null && (!field.get(builder).equals(""))){
                    if(!field.getName().equals("types") && !field.getName().equals("staffId")
                            && !field.getName().startsWith("area") && !field.getName().startsWith("cost")
                            && !field.getName().startsWith("staff") && !field.getName().startsWith("createdBy")){
                        if(field.getType().getName().equals("java.lang.String")){
                            sql.append("AND b."+field.getName().toLowerCase()+" LIKE '%"+field.get(builder)+"%' ");
                        } else if(field.getType().getName().equals("java.lang.Integer")){
                            sql.append("AND b."+field.getName().toLowerCase()+" = "+field.get(builder)+" ");
                        }
                    }
                }
            }
            return sql;
        }catch (Exception ex){
            return null;
        }
    }
}
