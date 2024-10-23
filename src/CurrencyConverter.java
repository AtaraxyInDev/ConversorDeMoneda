import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    private static final String API_KEY = "04f9a274914aa95679162c31"; // Tu clave API
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";
    private static Map<String, Double> conversionRates;

    public static void main(String[] args) {
        try {
            // Construir el cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Construir la solicitud
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .GET()
                    .build();

            // Enviar la solicitud y recibir la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar el código de estado HTTP
            int statusCode = response.statusCode();
            System.out.println("Código de estado HTTP: " + statusCode);

            // Parsear  la respuesta usando Gson
            Gson gson = new Gson();
            ExchangeRateResponse ratesResponse = gson.fromJson(response.body(), ExchangeRateResponse.class);

            // Verifica si la respuesta es exitosa
            if ("success".equals(ratesResponse.getResult())) {
                conversionRates = ratesResponse.getConversionRates();
                // Filtrar y mostrar las tasas de cambio deseadas
                List<String> desiredCurrencies = Arrays.asList("ARS", "COP", "BRL", "USD");
                filterAndPrintRates(conversionRates, desiredCurrencies);

                // Menú interactivo
                Scanner scanner = new Scanner(System.in);
                boolean running = true;

                while (running) {
                    System.out.println("\n--- Menú Interactivo ---");
                    System.out.println("1. Convertir moneda");
                    System.out.println("2. Ver tasas de cambio");
                    System.out.println("3. Salir");
                    System.out.print("Seleccione una opción: ");

                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            performConversion(scanner);
                            break;
                        case 2:
                            filterAndPrintRates(conversionRates, desiredCurrencies);
                            break;
                        case 3:
                            running = false;
                            System.out.println("Saliendo del programa.");
                            break;
                        default:
                            System.out.println("Opción inválida, por favor intente de nuevo.");
                    }
                }
                scanner.close();
            } else {
                System.out.println("Error en la respuesta de la API: " + ratesResponse.getResult());
            }

        } catch (Exception e) {
            System.err.println("Error al realizar la solicitud: " + e.getMessage());
        }
    }

    // Método para filtrar y mostrar las tasas de cambio de las monedas deseadas
    private static void filterAndPrintRates(Map<String, Double> conversionRates, List<String> desiredCurrencies) {
        System.out.println("Tasas de cambio:");
        for (String currency : desiredCurrencies) {
            if (conversionRates.containsKey(currency)) {
                System.out.println("Tasa de cambio para " + currency + ": " + conversionRates.get(currency));
            } else {
                System.out.println("No se encontró tasa para " + currency);
            }
        }
    }

    // Método para realizar la conversión de moneda
    private static void performConversion(Scanner scanner) {
        System.out.print("Ingrese la cantidad a convertir: ");
        double amountToConvert = scanner.nextDouble();
        System.out.print("Ingrese el código de la moneda de origen (ej. COP): ");
        String fromCurrency = scanner.next().toUpperCase();
        System.out.print("Ingrese el código de la moneda de destino (ej. ARS, COP, BRL, USD): ");
        String toCurrency = scanner.next().toUpperCase();

        // Realizar la conversión
        double result = convertCurrency(amountToConvert, fromCurrency, toCurrency, conversionRates);
        if (result != 0.0) {
            System.out.printf("%.2f %s es igual a %.2f %s%n", amountToConvert, fromCurrency, result, toCurrency);
        }
    }

    // Método para convertir entre monedas
    private static double convertCurrency(double amount, String fromCurrency, String toCurrency, Map<String, Double> conversionRates) {
        if (conversionRates.containsKey(fromCurrency) && conversionRates.containsKey(toCurrency)) {
            // Obtener las tasas de conversión y calcular la conversión en una sola línea
            return (amount / conversionRates.get(fromCurrency)) * conversionRates.get(toCurrency);
        } else {
            System.out.println("Una o ambas monedas no están disponibles.");
            return 0.0; // Devolver 0 o un valor especial en caso de error
        }
    }
}