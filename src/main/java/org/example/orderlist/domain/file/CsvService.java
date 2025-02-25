package org.example.orderlist.domain.file;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.example.orderlist.domain.product.Product;
import org.example.orderlist.domain.product.ProductRepository;
import org.example.orderlist.domain.purchase.Purchase;
import org.example.orderlist.domain.user.User;
import org.example.orderlist.domain.user.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvService {

    private final Gson gson;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void uploadUserCsv(MultipartFile file) throws Exception {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextRecord;
            List<User> users = new ArrayList<>();

            // Pass Header
            csvReader.readNext();

            while ((nextRecord = csvReader.readNext()) != null) {
                String name = nextRecord[0];
                String email = nextRecord[1];
                String purchases = nextRecord[2];

                // JSON -> List<Purchase>
                Type purchaseListType = new TypeToken<List<Purchase>>() {}.getType();
                List<Purchase> purchaseList = gson.fromJson(purchases, purchaseListType);

                User user = User.builder()
                        .name(name)
                        .email(email)
                        .purchases(purchaseList)
                        .build();

                users.add(user);
            }

            userRepository.saveAll(users);
        }
    }

    public void uploadProductCsv(MultipartFile file) throws Exception {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] nextRecord;
            List<Product> products = new ArrayList<>();

            // Pass Header
            csvReader.readNext();

            while ((nextRecord = csvReader.readNext()) != null) {
                String name = nextRecord[0];
                Long price = Long.parseLong(nextRecord[1]);

                Product product = Product.builder()
                        .name(name)
                        .price(price)
                        .build();

                products.add(product);
            }

            productRepository.saveAll(products);
        }
    }

    public byte[] downloadUserCsv() throws Exception {
        try{
            List<User> userList = userRepository.findAll();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream));

            // CSV Header
            String[] header = {"id", "name", "email", "purchases"};
            csvWriter.writeNext(header);

            // 사용자 데이터로 CSV 작성
            for (User user : userList) {
                String[] data = {
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        gson.toJson(user.getPurchases())
                };
                csvWriter.writeNext(data);
            }

            csvWriter.close();

            // CSV ->  byte[]
            return outputStream.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }
}
