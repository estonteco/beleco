/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estonteco.spark.frames.conf.factory.serializers;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypeConversions;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

/**
 *
 * @author APolokh
 */
public class StructTypeJAXBAdapter extends XmlAdapter<ListColumns, StructType> {

    public StructTypeJAXBAdapter() {
    }

    //todo: Map<String,MetaInfo>
    @Override
    public StructType unmarshal(ListColumns list) throws Exception {
        List<StructField> structFields = new ArrayList<StructField>();
        for (ColumnInfo field : list.getColumns()) {
            DataType dt = DataType.fromCaseClassString(field.getType());
            StructField columnType = DataTypes.createStructField(field.getName(), dt, field.isNullable());
            structFields.add(columnType);
        }
        return DataTypes.createStructType(structFields);
    }

    @Override
    public ListColumns marshal(StructType st) throws Exception {
        StructField[] fields = st.fields();
        ArrayList<ColumnInfo> list = new ArrayList<ColumnInfo>();
        for (StructField structField : fields) {
            String simpleName = structField.dataType().getClass().getSimpleName();
            String typeName = simpleName.substring(0, simpleName.length() - 1);
            list.add(new ColumnInfo(structField.name(),
                    typeName,
                    structField.nullable()));
        }
        return new ListColumns(list);
    }

}
