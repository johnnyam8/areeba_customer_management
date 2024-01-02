package com.areeba.areeba_customer_management.configuration;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.context.annotation.Configuration;

/**
 * custom class to map naming between column names and the database columns
 * @author Johnny
 */
@Configuration
public class AreebaNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        return Identifier.toIdentifier(name.getText(), true);
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        if (name != null) {
            String colName = name.getText();
            String firstCharacter = String.valueOf(colName.charAt(0));
            firstCharacter = firstCharacter.toUpperCase();
            colName = firstCharacter + colName.substring(1);
            return Identifier.toIdentifier(colName, true);
        } else {
            return name;
        }
    }

}
