package com.company;
import weka.associations.Apriori;
import weka.core.Instances;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.File;

public class Main {

    public static Instances readFromArff() throws Exception
    {
        DataSource source = new DataSource("/home/student/git/CS6310_Team45/WekaImplementation/requests.arff");
        Instances data = source.getDataSet();
        return data;
    }

    public static Instances readFromDB() throws Exception
    {
        InstanceQuery query = new InstanceQuery();
        //query.setDatabaseURL("jdbc:mysql://localhost:3306/A10db?autoReconnect=true&useSSL=false");
        query.setCustomPropsFile(new File("DatabaseUtils.props"));
        query.setUsername("root");
        query.setPassword("cs6310");
        //query.setQuery("select CONVERT(uusid, CHAR CHARACTER SET utf8) ,CONVERT(courseid, CHAR CHARACTER SET utf8) from AcademicRecord");
        query.setQuery("select uusid, courseid from AcademicRecord");
        return query.retrieveInstances();
    }

    public static void main(String[] args) throws Exception {
	// write your code here
        /*BufferedReader reader = new BufferedReader(
                new FileReader("/some/where/data.arff"));
        Instances data = new Instances(reader);
        reader.close();
        */
        Instances data = readFromArff();
        //Instances data = readFromDB();

        // setting class attribute if the data format does not provide this information
        // For example, the XRFF format saves the class attribute information as well
        int nAttr = data.numAttributes();
        int index = (int) (Math.random() * nAttr);
        data.setClassIndex(index);
        //if (data.classIndex() == -1)
            //data.setClassIndex(data.numAttributes() - 1);
        NumericToNominal filter1= new  NumericToNominal();
        String[] options = new String[2];
        options[0] = "-R";
        options[1]= "2";
        filter1.setOptions(options);
        filter1.setInputFormat(data);
        Instances filtered_data = Filter.useFilter(data,filter1);
        //System.out.println(filtered_data);
        DenormalizeTag filter2 = new DenormalizeTag();
        //String[] options2 = new String[1];
        //options[1]="-B";
        filter2.setGroupingAttribute("1");
        filter2.setInputFormat(filtered_data);
        Instances filtered_data2 = Filter.useFilter(filtered_data,filter2);
       // filter2.runFilter(filter2, options2);
        //System.out.println(filtered_data2);
        Remove filter3= new Remove();
        String[] options3= new String[2];
        options3[0]= "-R";
        options3[1]="1";
        filter3.setOptions(options3);
        filter3.setInputFormat(filtered_data2);
        Instances filtered_data3 = Filter.useFilter(filtered_data2,filter3);
        //System.out.println("filtered_data3");
       // SelectedTag tag = filter2.getAggregationType();
       // System.out.println("tag is below");
        //System.out.println(tag);
        //System.out.println(filtered_data3);
        Apriori algo = new Apriori();
        algo.setNumRules(50);
        try{
            algo.buildAssociations(filtered_data3);
        }catch(Exception e){
            e.printStackTrace();
        }
        String rules=algo.toString();
        System.out.println("Apriori output :");
        System.out.println(rules);

    }

}