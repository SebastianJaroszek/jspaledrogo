package pl.dominisz;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.rmi.Naming;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Przechowuje informacje o produktach dostępnych w sklepie
 * - lista produktów
 * - konstruktor tworzący kilka produktów
 * - znalezienie wszystkich produktów
 * - znalezienie produktu o podanym id
 * - zmniejszenie stanu produktu o podanym id, o podaną liczbę sztuk
 */
public class ProductRepository {

    private static ProductRepository instance;

    public static ProductRepository getInstance() {
        if (instance == null) {
            instance = new ProductRepository();
        }
        return instance;
    }

    private DataSource dataSource;

    private ProductRepository() {
        try {
            InitialContext initialContext = new InitialContext();
            Context context = (Context) initialContext.lookup("java:/comp/env");
            dataSource = (DataSource) context.lookup("jdbc/jsp_schema");
        } catch (NamingException e) {

        }
    }

    /**
     * Zwraca listę wszystkich produktów ze sklepu
     *
     * @return lista wszystkich produktów
     */
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        try {

            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Product product = createProduct(resultSet);
                products.add(product);
            }

            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    private Product createProduct(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");//lub 1
        String name = resultSet.getString("name");//lub 2
        String description = resultSet.getString("description");//lub 3
        BigDecimal price = resultSet.getBigDecimal("price");//lub 4
        int count = resultSet.getInt("count");//lub 5
        return new Product(id, name, description, price, count);
    }

    /**
     * Zwraca produkt o podanym id
     *
     * @param id id produktu do wyszukania
     * @return znaleziony produkt, null jeżeli nie znaleziono produktu
     */
    public Product findById(int id) {
        Product product = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                product = createProduct(resultSet);
            }

            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    /**
     * Ustawia liczbę sztuk produktu w magazynie
     *
     * @param id    id produktu
     * @param count nowa liczba sztuk produktu
     */
    public void setCount(int id, int count) {

    }
}
