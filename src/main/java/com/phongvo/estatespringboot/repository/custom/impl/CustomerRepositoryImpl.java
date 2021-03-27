package com.phongvo.estatespringboot.repository.custom.impl;

import com.phongvo.estatespringboot.dto.CustomerDto;
import com.phongvo.estatespringboot.entity.Customer;
import com.phongvo.estatespringboot.repository.custom.CustomerRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll(CustomerDto customerDto) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM customer AS c WHERE TRUE AND enable = 1 ");
        sql = buildSqlCommon(sql, customerDto);
        sql = buildSpecialSql(sql, customerDto);
        sql.append(" ORDER BY c.modifiedDate DESC ");
        Query query = entityManager.createNativeQuery(sql.toString(), Customer.class);
        return query.getResultList();
    }

    private StringBuilder buildSpecialSql(StringBuilder sql, CustomerDto model) {
        if(model.getStaffId() != null){
            sql.append(" AND EXISTS(SELECT * FROM assignmentcustomer as asi where asi.customer_id = c.id and asi.user_id = "+
                    model.getStaffId()+") ");
        }
        return sql;
    }


    private StringBuilder buildSqlCommon(StringBuilder sql, CustomerDto model){
        try{
            for(Field field :  CustomerDto.class.getDeclaredFields()){
                field.setAccessible(true);
                if(field.get(model) != null && !field.get(model).equals("")){
                    if(field.getType().getName().equals("java.lang.String")){
                        sql.append(" AND c."+field.getName().toLowerCase()+" LIKE '%"+field.get(model)+"%' ");
                    }else if(field.getType().getName().equals("java.lang.Integer")){
                        sql.append(" AND c."+field.getName().toLowerCase()+" = "+field.get(model)+" ");
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return sql;
    }
}
