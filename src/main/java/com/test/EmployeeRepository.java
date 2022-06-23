package com.test;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class EmployeeRepository {
    private Map<Integer, Employee> data = new HashMap<>();

    @PostConstruct
    public void init(){
        File file = new File("out/emp.txt");
        try {
            if(file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String str;
                Gson gson = new Gson();
                while ((str = reader.readLine()) != null) {
                    Employee e = gson.fromJson(str, Employee.class);
                    data.put(e.getId(), e);
                }
                reader.close();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
     }

    @PreDestroy
    public void commit(){
        File file = new File("out/emp.txt");
        try {
            if(!file.exists())
                file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            Gson gson = new Gson();
            for(Map.Entry<Integer, Employee> map : data.entrySet()){
                writer.write(gson.toJson(map.getValue())+"\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean contains(Integer id){
        return data.containsKey(id);
    }
    boolean save(Employee employee){
        if(data.containsKey(employee.getId()))
            return false;
        else {
            if(!data.containsKey(employee.getId())) {
                data.put(employee.getId(), employee);
            }
            return true;
        }
    }
    boolean delete(Employee employee){
        return data.remove(employee.getId())!=null;
    }
    boolean update(Employee employee){
        if(!data.containsKey(employee.getId()))
            return false;
        else {
            if(data.containsKey(employee.getId())) {
                data.put(employee.getId(), employee);
            }
            return true;
        }
    }
    public Employee findById(Integer id){
        return data.get(id);
    }
    public List<Employee> findAll(){
        return data.isEmpty() ? null :
                new ArrayList<Employee>(data.values())
                        .stream()
                        .sorted(Comparator.comparing(Employee::getId))
                        .collect(Collectors.toList());
    }
}