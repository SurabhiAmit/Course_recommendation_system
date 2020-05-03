package edu.gatech.coursemanagement.weka;

import edu.gatech.coursemanagement.exception.CourseManagementRuntimeException;
import edu.gatech.coursemanagement.utils.CourseManagementUtil;
import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.WekaException;
import weka.core.converters.ConverterUtils;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.Remove;

public class CourseManagementApriori
{

    private static Instances readFromArff() throws Exception
    {
        ConverterUtils.DataSource source = new ConverterUtils.DataSource("/home/student/git/CS6310_Team45/WekaImplementation/requests.arff");
        Instances data = source.getDataSet();
        return data;
    }

    private static Instances readFromDB() throws Exception
    {
        InstanceQuery query = new InstanceQuery();
        query.initialize( CourseManagementUtil.getProperties() );
        query.setUsername("root");
        query.setPassword("cs6310");
        query.setQuery("select uusid as STUDENT_ID,courseid as COURSE from AcademicRecord order by uusid");
        return query.retrieveInstances();
    }

    public String runWekaAlgo( int numOfRules ) throws Exception {
        Instances data = readFromDB();
        //Instances data = readFromArff();
        int nAttr = data.numAttributes();
        int index = (int) (Math.random() * nAttr);
        data.setClassIndex(index);
        NumericToNominal filter1= new  NumericToNominal();
        String[] options = new String[2];
        options[0] = "-R";
        options[1]= "2";
        filter1.setOptions(options);
        filter1.setInputFormat(data);
        Instances filtered_data = Filter.useFilter(data,filter1);
        DenormalizedCustom filter2 = new DenormalizedCustom();
        filter2.setGroupingAttribute("1");
        filter2.setInputFormat(filtered_data);
        Instances filtered_data2 = Filter.useFilter(filtered_data,filter2);
        Remove filter3= new Remove();
        String[] options3= new String[2];
        options3[0]= "-R";
        options3[1]="1";
        filter3.setOptions(options3);
        filter3.setInputFormat(filtered_data2);
        Instances filtered_data3 = Filter.useFilter(filtered_data2,filter3);
        Apriori algo = new Apriori();
        algo.setNumRules(numOfRules);
        algo.setMetricType( new SelectedTag(0,algo.TAGS_SELECTION));
        algo.setLowerBoundMinSupport(0.1d);
        algo.setMinMetric(0.5d);
        try{
            algo.buildAssociations(filtered_data3);
        }
        catch(WekaException e)
        {
            throw new CourseManagementRuntimeException("There is no input data.");
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        String rules=algo.toString();
        return rules;
    }
}
