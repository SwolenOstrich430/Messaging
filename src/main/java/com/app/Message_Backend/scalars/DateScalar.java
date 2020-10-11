package com.app.Message_Backend.scalars;

import graphql.language.StringValue;
import graphql.schema.*;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

@Component
class DateScalar extends GraphQLScalarType {
    private static final String NAME = "Date";
    private static final String DESCRIPTION = "Date value";

    public DateScalar() {
        super(NAME, DESCRIPTION, new Coercing<Date, String>() {
                SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");

                @Override
                public String serialize(Object input) throws CoercingParseLiteralException {
                    return input == null ? null : input.toString();
                }

                @Override
                public Date parseValue(Object input) {
                    try {
                        return input == null ? null : format.parse(input.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                        throw new CoercingParseLiteralException();
                    }
                }

                @Override
                public Date parseLiteral(Object input) {
                    if (input instanceof StringValue) {
                        try {
                            return format.parse(((StringValue) input).getValue());
                        } catch (DateTimeParseException | ParseException e) {
                            throw new CoercingParseLiteralException(e);
                        }
                    }

                    throw new CoercingParseLiteralException();
                }
        });
    }
}
