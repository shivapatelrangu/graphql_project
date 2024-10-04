//package com.room.reservation.api.domain.scalars;
//
//import graphql.language.StringValue;
//import graphql.schema.Coercing;
//import graphql.schema.GraphQLScalarType;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class DateScalar {
//
//    public static final GraphQLScalarType DATE = GraphQLScalarType.newScalar()
//            .name("Date")
//            .description("Custom Date scalar")
//            .coercing(new Coercing<Date, String>() {
//                private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//                @Override
//                public String serialize(Object dataFetcherResult) {
//                    return dateFormat.format((Date) dataFetcherResult);
//                }
//
//                @Override
//                public Date parseValue(Object input) {
//                    try {
//                        return dateFormat.parse(input.toString());
//                    } catch (ParseException e) {
//                        throw new RuntimeException("Invalid date format");
//                    }
//                }
//
//                @Override
//                public Date parseLiteral(Object input) {
//                    if (input instanceof StringValue) {
//                        try {
//                            return dateFormat.parse(((StringValue) input).getValue());
//                        } catch (ParseException e) {
//                            throw new RuntimeException("Invalid date format");
//                        }
//                    }
//                    throw new RuntimeException("Invalid date format");
//                }
//            })
//            .build();
//}
