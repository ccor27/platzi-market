package com.platzi.market.persistence.mapper;

import com.platzi.market.domain.PurchaseItem;
import com.platzi.market.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface PurchaseItemMapper {

    @Mappings({
            //lo que esta en el target, son los atributos de PurchaseItem
            //lo que esta en source son los atributos de ComprasProducto
            //aca le decimos al mapper, que vamos a convertir de ComprasProducto a PurchaseItem
            @Mapping(source = "id.idProducto",target = "productId"),
            @Mapping(source = "cantidad",target = "quantity"),
            @Mapping(source = "estado",target = "active")
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    @InheritInverseConfiguration
    @Mappings({
            //como estos atributos no los estamos mapeando en PurchaseItem
            //lo que hacemos es ignorarlo al momento de convertir el PurchaseItem a ComprasProducto
            @Mapping(target = "compra",ignore = true),
            @Mapping(target = "producto",ignore = true),
            @Mapping(target = "id.idCompra",ignore = true)
    })
    ComprasProducto toComprasProducto(PurchaseItem item);
}
