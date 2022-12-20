package com.olusegun.service.cdItem;

import com.olusegun.data.model.Cd;

import java.util.List;

public interface CdItemService {

        Cd getItemById(long id);

        String validateItemId(long id);

        long findItemIdByLoanId(long loanId);

        List<Cd> getAllItems();

        void saveItem(Cd item);

        String validateItemId(String itemName, String itemType);

        void deleteItem(long itemId);






}


