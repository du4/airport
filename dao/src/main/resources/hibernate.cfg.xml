<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
    <session-factory>

        <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
        <property name="hibernate.connection.isolation">2</property>

        <property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
        <property name="hibernate.cache.use_second_level_cache">true</property>
        <property name="hibernate.cache.use_query_cache">true</property>
        <property name="net.sf.ehcache.configurationResourceName">ehcache.xml</property>


        <property name="hibernate.connection.provider_class">com.zaxxer.hikari.hibernate.HikariConnectionProvider</property>
        <property name="hibernate.hikari.dataSource.cachePrepStmts">true</property>
        <property name="hibernate.hikari.maximumPoolSize">10</property>
        <property name="hibernate.hikari.dataSource.prepStmtCacheSize">250</property>
        <property name="hibernate.hikari.dataSource.prepStmtCacheSqlLimit">2048</property>
        <property name="hibernate.hikari.dataSource.useServerPrepStmts">true</property>
        <property name="hibernate.hikari.dataSourceClassName">com.mysql.jdbc.jdbc2.optional.MysqlDataSource</property>
        <property name="hibernate.hikari.dataSource.url">jdbc:mysql://localhost:3306/airportH?useUnicode=true&amp;characterEncoding=utf8</property>
        <property name="hibernate.hikari.dataSource.user">root</property>
        <property name="hibernate.hikari.dataSource.password">root</property>
        <property name="hibernate.hikari.idleTimeout">30000</property>
        <property name="hibernate.hikari.minimumIdle">1</property>

        <property name="show_sql">true</property>
        <property name="hibernate.hbm2ddl.auto">validate</property>

        <mapping class="by.it.pvt.du4.beans.Role"/>
        <mapping class="by.it.pvt.du4.beans.User"/>
        <mapping class="by.it.pvt.du4.beans.Command"/>
        <mapping class="by.it.pvt.du4.beans.Flight"/>
        <mapping class="by.it.pvt.du4.beans.Permission"/>
        <mapping class="by.it.pvt.du4.beans.Pilot"/>
        <mapping class="by.it.pvt.du4.beans.Plane"/>
        <mapping class="by.it.pvt.du4.beans.Airhostess"/>
        <mapping class="by.it.pvt.du4.beans.Airport"/>
    </session-factory>
</hibernate-configuration>

