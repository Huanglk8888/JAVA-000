package com.hlk.homework.weeks08.dao.domain;

import lombok.Data;


@Data
public class Order {
    private Long orderId;
    private Long userId;
    private Long goodsId;
}
