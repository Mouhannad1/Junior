
package com.example.mouhannad.drawer;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class menu2_Fragment extends Fragment {

    public  menu2_Fragment(){}

    ArrayList<Actors> actorsList;

    ActorAdapter adapter;



    View rootview;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.menu2_layout, container, false);
        String category = getArguments().getString("category");

        actorsList = new ArrayList<Actors>();



        if (category.contains("diamond")) {
            new JSONAsyncTask().execute("http://192.168.43.96/api/product/getdiamondproducts");
        }
        else if  (category.contains("electronics")){
            new JSONAsyncTask().execute("http://192.168.43.96/api/product/getElectronicproducts");
        }
        else if (category.contains("watches")){
            new JSONAsyncTask().execute("http://192.168.43.96/api/product/geteWatcheproducts");
        }

        final ListView listview = (ListView) rootview.findViewById(R.id.list);




        adapter = new ActorAdapter(getActivity().getApplicationContext(), R.layout.row, actorsList);

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id) {

                Actors act = adapter.getItem(position);
                Intent intent = new Intent(menu2_Fragment.this.getActivity(), details.class);

                intent.putExtra("Name",act.getName());
                intent.putExtra("Image", act.getImage());
                intent.putExtra("Description", act.getDescription());
                intent.putExtra("Price", act.getDob());
                menu2_Fragment.this.startActivity(intent);

            }
        });



        return rootview;

    }




    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {

                //------------------>>
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);



                    JSONArray jarray = new JSONArray(data);
//					JSONObject jsono = new JSONObject(data);
//					JSONArray jarray = jsono.getJSONArray("Actors");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);

                        Actors actor = new Actors();

                        actor.setName(object.getString("Name"));
                        actor.setDescription(object.getString("Description"));

                        actor.setImage(object.getString("Image"));
                        actor.setDob(object.getString("Price"));



                        actorsList.add(actor);
                    }
                    return true;
                }

                //------------------>>

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            adapter.notifyDataSetChanged();
            if(result == false)
                Toast.makeText(getActivity().getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }

    }






}
