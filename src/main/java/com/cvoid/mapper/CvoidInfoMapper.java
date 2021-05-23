package com.cvoid.mapper;

import com.cvoid.dto.CvoidInfoDTO;
import com.cvoid.entity.CvoidInfo;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.metadata.Type;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CvoidInfoMapper extends ConfigurableMapper {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void configure(MapperFactory factory) {
        ConverterFactory converterFactory = factory.getConverterFactory();
        converterFactory.registerConverter("objectDateConverter", new ObjectDateConverter());
        converterFactory.registerConverter("objectLongConverter", new ObjectLongConverter());
        converterFactory.registerConverter("objectDoubleConverter", new ObjectDoubleConverter());
        super.configure(factory);
        factory.registerClassMap(factory.classMap(CvoidInfoDTO.class, CvoidInfo.class)
                .fieldMap("lastUpdate", "lastUpdate").converter("objectDateConverter").add()
                .fieldMap("lat", "lat").converter("objectDoubleConverter").add()
                .fieldMap("lng", "lng").converter("objectDoubleConverter").add()
                .fieldMap("confirmed", "confirmed").converter("objectLongConverter").add()
                .fieldMap("deaths", "deaths").converter("objectLongConverter").add()
                .fieldMap("recovered", "recovered").converter("objectLongConverter").add()
                .fieldMap("active", "active").converter("objectLongConverter").add()
                .fieldMap("incidentRate", "incidentRate").converter("objectDoubleConverter").add()
                .fieldMap("caseFatalityRatio", "caseFatalityRatio").converter("objectDoubleConverter").add()
                .byDefault()
                .toClassMap());
    }

    public static class ObjectDateConverter extends BidirectionalConverter<Object, Date> {

        @Override
        public Date convertTo(Object o, Type<Date> dateType,  MappingContext mappingContext) {
            if (o == null) {
                return null;
            } else {
                try {
                    return dateFormat.parse(o.toString());
                } catch (ParseException e) {
                    return null;
                }
            }
        }

        @Override
        public Object convertFrom(Date date, Type<Object> objectType,  MappingContext mappingContext) {
            if (date == null) {
                return null;
            } else {
                return dateFormat.format(date);
            }
        }
    }

    public static class ObjectLongConverter extends BidirectionalConverter<Object, Number> {

        @Override
        public Number convertTo(Object o, Type<Number> dateType,  MappingContext mappingContext) {
            if (o == null) {
                return null;
            } else if(NumberUtils.isParsable(o.toString())) {
                return NumberUtils.createLong(o.toString());
            }
            return 0l;
        }

        @Override
        public Object convertFrom(Number number, Type<Object> objectType,  MappingContext mappingContext) {
            if (number == null) {
                return null;
            } else {
                return number.toString();
            }
        }
    }

    public static class ObjectDoubleConverter extends BidirectionalConverter<Object, Number> {

        @Override
        public Number convertTo(Object o, Type<Number> dateType,  MappingContext mappingContext) {
            if (o == null) {
                return null;
            } else if(NumberUtils.isParsable(o.toString())) {
                return NumberUtils.createDouble(o.toString());
            }
            return 0.00;
        }

        @Override
        public Object convertFrom(Number number, Type<Object> objectType,  MappingContext mappingContext) {
            if (number == null) {
                return null;
            } else {
                return number.toString();
            }
        }
    }
}


