package br.com.fiap.tech.challenge.adapter.driven.mysql.service;

import br.com.fiap.tech.challenge.adapter.driven.mysql.mapping.PaymentMapper;
import br.com.fiap.tech.challenge.adapter.driven.mysql.mapping.PurchaseItemMapper;
import br.com.fiap.tech.challenge.adapter.driven.mysql.mapping.PurchaseMapper;
import br.com.fiap.tech.challenge.adapter.driven.mysql.model.PurchaseEntity;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.PaymentEntityRepository;
import br.com.fiap.tech.challenge.adapter.driven.mysql.repository.PurchaseEntityRepository;
import br.com.fiap.tech.challenge.domain.entity.Purchase;
import br.com.fiap.tech.challenge.port.driven.PurchaseWriterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseEntityWriterService implements PurchaseWriterService {

    private final PurchaseMapper purchaseMapper;
    private final PurchaseItemMapper purchaseItemMapper;
    private final PaymentMapper paymentMapper;
    private final PurchaseEntityRepository purchaseRepository;
    private final PaymentEntityRepository paymentRepository;

    public PurchaseEntityWriterService(
            PurchaseMapper purchaseMapper,
            PurchaseItemMapper purchaseItemMapper,
            PaymentMapper paymentMapper,
            PurchaseEntityRepository purchaseRepository,
            PaymentEntityRepository paymentRepository
    ) {
        this.purchaseMapper = purchaseMapper;
        this.purchaseItemMapper = purchaseItemMapper;
        this.paymentMapper = paymentMapper;
        this.purchaseRepository = purchaseRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public Purchase write(Purchase purchase) {
        var purchaseEntity = savePurchase(purchase);

        var paymentEntity = paymentMapper.toPaymentEntity(purchase.payment());
        paymentEntity.setPurchase(purchaseEntity);

        paymentRepository.save(paymentEntity);

        return purchaseEntity.toDomain(purchaseMapper);
    }

    private PurchaseEntity savePurchase(Purchase purchase) {
        var purchaseEntity = purchaseMapper.toPurchaseEntity(purchase);

        purchase.items().stream()
                .map(purchaseItemMapper::toPurchaseItemEntity)
                .forEach(purchaseEntity::addItem);

        return purchaseRepository.save(purchaseEntity);
    }
}
