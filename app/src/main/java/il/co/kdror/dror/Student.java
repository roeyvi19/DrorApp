package il.co.kdror.dror;

/**
 * Created by User on 25/01/2017.
 */

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.lang.reflect.GenericSignatureFormatError;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Student
{
    private String id;
    private String name;
    private String location;
    private String phone;

    public Student(String id, String name, String location, String phone)
    {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
    }

    public String GetId() {return id;}

    public String GetName() {return name;}

    public String GetLocation() {return location;}

    public String GetPhone() {return phone;}

    public String Serialze()
    {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("location", location);
        map.put("phone", phone);

        Type mapType = new TypeToken<Map<String, Number>>() {}.getType();

        Gson gson = new GsonBuilder().create();

        return gson.toJson(map, mapType);

    }

    public static Student Deserialze(String json)
    {
        Gson gson = new Gson();

        return gson.fromJson(json, Student.class);
    }


}
