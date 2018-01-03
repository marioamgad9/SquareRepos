package com.mouris.mario.squarerepos.utils;

import android.util.Log;

import com.mouris.mario.squarerepos.data.Repo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private static final String REQUEST_URL = "https://api.github.com/users/square/repos";

    //A private constructor to prevent initiating the class
    private QueryUtils() {
    }

    static List<Repo> fetchReposData() {
        URL url = createUrl(REQUEST_URL);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        return extractReposFromJson(jsonResponse);
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "There was an error while creating the URL ", e);
        }

        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            int response_code = urlConnection.getResponseCode();
            if (response_code == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code " + response_code);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }

        return output.toString();
    }

    private static List<Repo> extractReposFromJson(String reposJSON) {
        List<Repo> reposList = new ArrayList<>();

        try {
            JSONArray jsonRepoArray = new JSONArray(reposJSON);
            for (int i=0 ; i < jsonRepoArray.length(); i++) {
                JSONObject jsonRepoObject = jsonRepoArray.getJSONObject(i);
                String name = jsonRepoObject.getString("name");
                String description = jsonRepoObject.getString("description");
                String url = jsonRepoObject.getString("html_url");
                String owner_name = jsonRepoObject.getJSONObject("owner").getString("login");
                String owner_url = jsonRepoObject.getJSONObject("owner").getString("html_url");
                boolean fork = jsonRepoObject.getBoolean("fork");

                reposList.add(new Repo(name, description, url, owner_name, owner_url, fork));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return reposList;
    }

}
