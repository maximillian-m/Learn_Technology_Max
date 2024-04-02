//package com.maximillian.logging.LoggingTutorial.controller;
//
//import com.maximillian.logging.AwsS3BucketService;
//import jakarta.annotation.PostConstruct;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//@Slf4j
//@Service
//public class TrialServiceImplementation {
//
//
//
//        private final AwsS3BucketService service;
//        @Value("${application.baseuri.shuzia}")
//        String BASE_URL_BOOK_COVER;
//
//    public TrialServiceImplementation(AwsS3BucketService service) {
//        this.service = service;
//    }
//
//    public void createSo() {
//        try {
//            String postString = "Hello-World";
//            byte[] stringTobyte = postString.getBytes();
//            String key = "postforshuzia";
//            service.uploadObject();
//
//        } catch (Exception ex) {
//            log.error("<============= error while processing ==============> {}", ex.getMessage());
//            ex.printStackTrace();
//        }
//    }
//    @PostConstruct
//    public void doPostConstruct(){
//        createSo();
//        log.info("===========> done <============");
//    }
////    @Override
////        public String createBook(MultipartFile multipartFile){
////            String key = "BookCover/" + UUID.randomUUID();
////            try {
////                service.uploadObject(key, multipartFile.getBytes());
////                Book book = getBookFromRequest(bookRequest, key, user, category);
////                checkForAccessibility(bookRequest, book, user);
////                book.setStatus(NEW);
////                book.setCategory(category.getName());
////                book.setSubCategory(bookRequest.getSubCategory());
////                Book savedBook = bookRepository.save(book);
////                initialRatingForProject(book.getId(), user);
////                ListBookResponse listBookResponse = bookResponse(savedBook, user);
////                return new GenericResponse(true, "success", listBookResponse);
////            } catch (IOException e) {
////                throw new CustomException("error occurred while uploading file", HttpStatus.INTERNAL_SERVER_ERROR);
////            }
////        }
////
////        private void checkForAccessibility(BookRequest bookRequest, Book book, AppUser user) throws CustomException {
////            if (bookRequest.getBookAccessibility().equals(Accessibility.PAID)) {
////                book.setPrice(BigDecimal.valueOf(bookRequest.getPricing()));
////                book.setCurrency(getUserWalletCurrency(user.getId()));
////            }else{
////                book.setPrice(BigDecimal.valueOf(0.0));
////                book.setCurrency(NGN);
////            }
////        }
////
////        private Book getBookFromRequest(BookRequest bookRequest, String key, AppUser user, Category category) {
////            String bookCoverUrl = BASE_URL_BOOK_COVER + "?bookId=" + key;
////            return Book.getBookInstanceToSaveNewBook(
////                    bookRequest.getBookType(),
////                    user, bookRequest.getBookTitle(),
////                    bookRequest.getDescription(),
////                    bookCoverUrl, category.getId(),
////                    bookRequest.getBookAccessibility());
////        }
//}
