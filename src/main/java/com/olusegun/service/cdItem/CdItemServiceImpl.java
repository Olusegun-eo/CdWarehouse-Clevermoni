package com.olusegun.service.cdItem;


import com.olusegun.data.dto.CdDto;
import com.olusegun.data.model.Cd;
import com.olusegun.data.model.RecordLabel;
import com.olusegun.exceptions.CdNotExistException;
import com.olusegun.repository.CdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CdItemServiceImpl implements CdItemService {
//
//        @Autowired
//        private IItemRepository itemRepository;
//
//        @Override
//        public Cd getItemById(long id) {
//            Optional<Cd> optional = itemRepository.findById(id);
//            Cd item = null;
//            if (optional.isPresent()) {
//                item = optional.get();
//            } else {
//                // Exception
//            }
//            return item;
//        }
//
//        @Override
//        public String validateCdId(long id) {
//            String errorMessage = "";
//            Cd item = getItemById(id);
//            if (item == null) {
//                errorMessage = "Item id does not exist";
//            }
//            return errorMessage;
//        }
//
//        @Override
//        public List<Cd> getAllItems() {
//            return itemRepository.findAll();
//        }
//
//        @Override
//        public long findItemIdByLoanId(long loanId) {
//            List<Cd> itemList = getAllItems();
//            List<Loan> loanList;
//            boolean found = false;
//            long itemId = -1;
//            for (Cd item : itemList) {
//                loanList = item.getLoan();
//                for (Loan loan : loanList) {
//                    if (loan.getId() == loanId) {
//                        itemId = item.getId();
//                        found = true;
//                        break;
//                    }
//                }
//                if (found) {
//                    break;
//                }
//            }
//            return itemId;
//        }
//
//        @Override
//        public void saveCd(Cd item) {
//            itemRepository.save(item);
//        }
//
//        @Override
//        public String validateCdId(String itemName, String itemType) {
//            String errorMessage = "";
//            List<Cd> itemList = itemRepository.findAll();
//            for (Cd item : itemList) {
//                if (item.getName().equalsIgnoreCase(itemName)
//                        & item.getItemType().getTypeName().equalsIgnoreCase(itemType)) {
//                    errorMessage = "Item already exists in the database. Cannot add.";
//                }
//            }
//            return errorMessage;
//        }
//
//        @Override
//        public void deleteCd(long itemId) {
//            Cd item = getItemById(itemId);
//            itemRepository.delete(item);
//        }
//
//
//
//
//
//    @Service
//    public class ProductService {
        @Autowired
        private CdRepository cdRepository;

        public List<CdDto> listProducts() {
            List<Cd> cds = cdRepository.findAll();
            List<CdDto> cdDtos = new ArrayList<>();
            for(Cd cd : cds) {
                CdDto cdDto = getDtoFromProduct(cd);
                cdDtos.add(cdDto);
            }
            return cdDtos;
        }

        public static CdDto getDtoFromProduct(Cd cd) {
            CdDto productDto = new CdDto(cd);
            return productDto;
        }

        public static Cd getProductFromDto(CdDto cdDto, RecordLabel recordLabel) {
            Cd cd = new Cd(cdDto, recordLabel);
            return cd;
        }

        public void addProduct(CdDto cdDto, RecordLabel recordLabel) {
            Cd product = getProductFromDto(cdDto, recordLabel);
            cdRepository.save(product);
        }

        public void updateProduct(Long cdID, CdDto cdDto, RecordLabel recordLabel) {
            Cd cd = getProductFromDto(cdDto, recordLabel);
            cd.setId(cdID);
            cdRepository.save(cd);
        }


        public Cd getProductById(Long cdId) throws CdNotExistException {
            Optional<Cd> optionalProduct = cdRepository.findById(cdId);
            if (!optionalProduct.isPresent())
                throw new CdNotExistException("Product id is invalid " + cdId);
            return optionalProduct.get();
        }
}
