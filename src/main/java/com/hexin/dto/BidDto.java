/**
 * Copyright(c) 2011-2015 by hexin Inc.
 * All Rights Reserved
 */
package com.hexin.dto;

import com.hexin.domain.hexin6.BidDocument;

/**
 * @author yanshun@hexindai.com
 */
public class BidDto {
    public BidDocument oldBid;
    public BidDocument newBid;

    public BidDto(BidDocument oldBid, BidDocument newBid) {
        this.oldBid = oldBid;
        this.newBid = newBid;
    }
}
