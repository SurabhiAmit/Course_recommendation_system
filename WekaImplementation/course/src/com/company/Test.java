package com.company;
import weka.associations.Apriori;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.NumericToNominal;
import java.io.BufferedReader;
import java.io.FileReader;
import weka.core.converters.ConverterUtils.DataSource;

public class Test {
    public static void main(String[] args) throws Exception {
        // write your code here
        /*BufferedReader reader = new BufferedReader(
                new FileReader("/some/where/data.arff"));
        Instances data = new Instances(reader);
        reader.close();
        */
        DataSource source = new DataSource("/home/student/git/CS6310_Team45/WekaImplementation/requests.arff");
        Instances data = source.getDataSet();

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
        System.out.println(filtered_data);
        DenormalizeTag filter2 = new DenormalizeTag();
        //String[] options2 = new String[1];
        //options[1]="-B";
        filter2.setGroupingAttribute("1");
        filter2.setInputFormat(filtered_data);
        Instances filtered_data2 = Filter.useFilter(filtered_data,filter2);
        // filter2.runFilter(filter2, options2);
        System.out.println(filtered_data2);
        Remove filter3= new Remove();
        String[] options3= new String[2];
        options3[0]= "-R";
        options3[1]="1";
        filter3.setOptions(options3);
        filter3.setInputFormat(filtered_data2);
        Instances filtered_data3 = Filter.useFilter(filtered_data2,filter3);
        System.out.println("filtered_data3");
        System.out.println(filtered_data3);
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
