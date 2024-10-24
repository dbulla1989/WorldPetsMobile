package com.example.worldpetsmobile.globalresource;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GlobalResource {

    private final String endpoint = "http://danbul1989-001-site1.ctempurl.com/";
    private static GlobalResource instance;
    private final OkHttpClient client;

    public GlobalResource (){
        client = new OkHttpClient();
    }

    public static GlobalResource getInstance() {
        if (instance == null) {
            instance = new GlobalResource();
        }
        return instance;
    }

    public String SendRequest(String uri, String method, String jsonBody) throws IOException {
        /*Request request = new Request.Builder()
                .url(endpoint + url)
                .build();*/
        Request request;
        RequestBody body = null;

        String url = endpoint + uri;

        // Si hay un cuerpo JSON, lo usamos en POST, PUT
        if (jsonBody != null && (method.equals("POST") || method.equals("PUT"))) {
            body = RequestBody.create(jsonBody, MediaType.parse("application/json; charset=utf-8"));
        }

        String credentials = Credentials.basic("11200336", "60-dayfreetrial");

        // Crear la petición según el método HTTP y agregar el header de Basic Auth
        switch (method) {
            case "GET":
                request = new Request.Builder()
                        .url(url)
                        .header("Authorization", credentials)
                        .get()
                        .build();
                break;

            case "POST":
                if (body == null) {
                    throw new IllegalArgumentException("POST requests must have a body");
                }
                request = new Request.Builder()
                        .url(url)
                        .header("Authorization", credentials)
                        .post(body)
                        .build();
                break;

            case "PUT":
                if (body == null) {
                    throw new IllegalArgumentException("PUT requests must have a body");
                }
                request = new Request.Builder()
                        .url(url)
                        .header("Authorization", credentials)
                        .put(body)
                        .build();
                break;

            case "DELETE":
                if (jsonBody != null) {
                    request = new Request.Builder()
                            .url(url)
                            .header("Authorization", credentials)
                            .delete(body)
                            .build();
                } else {
                    request = new Request.Builder()
                            .url(url)
                            .header("Authorization", credentials)
                            .delete()
                            .build();
                }
                break;

            default:
                throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }

        // Ejecutar la petición
        try (Response response = client.newCall(request).execute()) {
            /*if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }*/

            // Devolver el cuerpo de la respuesta como String
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
            return null;
        }
    }
}
