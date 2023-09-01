package br.com.fiap.tech.challenge.adapter.driven.mysql.mapping;

import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PaymentEntity;
import br.com.fiap.tech.challenge.enterprise.entity.Payment;
import br.com.fiap.tech.challenge.enterprise.enums.PaymentMethod;
import org.javamoney.moneta.Money;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.UUID;

import static br.com.fiap.tech.challenge.util.Mappings.moneyToBigDecimalConverter;
import static br.com.fiap.tech.challenge.util.Moneys.makeMoney;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PaymentMapper {

    @Mapping(target = "amount", source = "amount", qualifiedByName = "getAmount")
    @Mapping(target = "method", source = "method", qualifiedByName = "getPayment")
    @Mapping(target = "uuid", source = "uuid", qualifiedByName = "generateUuid")
    Payment toPayment(PaymentEntity payment);


    @Mapping(target = "amount", source = "payment", qualifiedByName = "moneyToBigDecimalConverter")
    @Mapping(target = "status", expression = "java(payment.status())")
    @Mapping(target = "uuid", expression = "java(payment.uuid().toString())")
    @Mapping(target = "date", expression = "java(payment.date())")
    @Mapping(target = "method", constant = "MERCADO_PAGO")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "lastUpdated", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "purchase", ignore = true)
    @Mapping(target = "version", ignore = true)
    PaymentEntity toPaymentEntity(Payment payment);


    @Named("getAmount")
    static Money getAmount(BigDecimal amount) {
        return makeMoney(amount);
    }


    @Named("moneyToBigDecimalConverter")
    static BigDecimal getAmount(Payment payment) {
        return moneyToBigDecimalConverter(payment.amount());
    }


    @Named("getPayment")
    static PaymentMethod getPayment(String method) {
        if ("MERCADO_PAGO".equals(method)) {
            return PaymentMethod.PAID_MARKET;
        }

        throw new IllegalArgumentException();
    }

    @Named("generateUuid")
    static UUID generateUuid(String uuid) {
        return UUID.fromString(uuid);
    }
}