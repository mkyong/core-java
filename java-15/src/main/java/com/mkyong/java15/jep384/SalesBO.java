package com.mkyong.java15.jep384;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.stream.Collectors.toList;

public class SalesBO {

    public static void main(String[] args) {

        SalesBO obj = new SalesBO();

        List<Merchant> merchants = Arrays.asList(new Merchant("mkyong"), new Merchant("Jack"), new Merchant("ABC"));

        List<Merchant> topMerchants = obj.findTopMerchants(merchants, 9);

        topMerchants.forEach(System.out::println);

    }

    List<Merchant> findTopMerchants(List<Merchant> merchants, int month) {

        // Local record
        record MerchantSales(Merchant merchant, double sales) {
        }

        return merchants.stream()
                .map(merchant -> new MerchantSales(merchant, computeSales(merchant, month)))
                .sorted((m1, m2) -> Double.compare(m2.sales(), m1.sales()))
                .map(MerchantSales::merchant)
                .collect(toList());
    }

    private double computeSales(Merchant merchant, int month) {
        // some business logic to get the sales...
        return ThreadLocalRandom.current().nextDouble(100, 10000);
    }
}
