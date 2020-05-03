package edu.gatech.coursemanagement.weka;


import weka.core.*;
import weka.filters.Filter;
import weka.filters.StreamableFilter;
import weka.filters.UnsupervisedFilter;

import java.util.*;

public class DenormalizedCustom extends Filter implements UnsupervisedFilter, OptionHandler, StreamableFilter
{
    private static final long serialVersionUID = -6334763153733741054L;
    public static final Tag[] TAGS_SELECTION;
    protected String m_groupingAttribute = "first";
    protected int m_groupingIndex = -1;
    protected boolean m_nonSparseMarketBasketFormat = false;
    protected boolean m_sparseFormat = true;
    protected DenormalizedCustom.NumericAggregation m_numericAggregation;
    private Map<String, Integer> m_newFormatIndexes;
    private double m_currentGroup;
    private double[] m_tempVals;
    private double[] m_counts;

    public DenormalizedCustom() {
        this.m_numericAggregation = DenormalizedCustom.NumericAggregation.SUM;
        this.m_newFormatIndexes = null;
        this.m_currentGroup = -1.0D;
        this.m_tempVals = null;
        this.m_counts = null;
    }

    public String globalInfo() {
        return "An instance filter that collapses instances with a common grouping ID value into a single instance. Useful for converting transactional data into a format that Weka's association rule learners can handle. IMPORTANT: assumes that the incoming batch of instances has been sorted on the grouping attribute. The values of nominal attributes are converted to indicator attributes. These can be either binary (with f and t values) or unary with missing values used to indicate absence. The later is Weka's old market basket format, which is useful for Apriori. Numeric attributes can be aggregated within groups by computing the average, sum, minimum or maximum.";
    }

    public Capabilities getCapabilities() {
        Capabilities result = super.getCapabilities();
        result.disableAll();
        result.enable(Capabilities.Capability.NO_CLASS);
        result.enableAllClasses();
        result.enable(Capabilities.Capability.NOMINAL_ATTRIBUTES);
        result.enable(Capabilities.Capability.NUMERIC_ATTRIBUTES);
        result.enable(Capabilities.Capability.DATE_ATTRIBUTES);
        result.enable(Capabilities.Capability.STRING_ATTRIBUTES);
        result.enable(Capabilities.Capability.MISSING_VALUES);
        return result;
    }

    protected void createOutputFormat(Instances instanceInfo) throws Exception {
        this.m_newFormatIndexes = new HashMap();
        ArrayList<Attribute> attInfo = new ArrayList();
        int count = 0;

        for(int i = 0; i < instanceInfo.numAttributes(); ++i) {
            if (i == this.m_groupingIndex) {
                attInfo.add(instanceInfo.attribute(this.m_groupingIndex));
                this.m_newFormatIndexes.put(instanceInfo.attribute(this.m_groupingIndex).name(), new Integer(count));
                ++count;
            } else if (instanceInfo.attribute(i).isNumeric()) {
                String newName = this.m_numericAggregation.toString() + "_" + instanceInfo.attribute(i).name();
                attInfo.add(new Attribute(newName));
                this.m_newFormatIndexes.put(newName, new Integer(count));
                ++count;
            } else if (instanceInfo.attribute(i).isNominal()) {
                for(int j = 0; j < instanceInfo.attribute(i).numValues(); ++j) {
                    ArrayList<String> vals = new ArrayList();
                    if (this.m_nonSparseMarketBasketFormat) {
                        vals.add("taken");
                    } else {
                        vals.add("none");
                        vals.add("taken");
                    }

                    String newName = instanceInfo.attribute(i).name() + "_" + instanceInfo.attribute(i).value(j);
                    attInfo.add(new Attribute(newName, vals));
                    this.m_newFormatIndexes.put(newName, new Integer(count));
                    ++count;
                }
            }
        }

        Instances outputFormat = new Instances(instanceInfo.relationName() + "_denormalized", attInfo, 0);
        this.setOutputFormat(outputFormat);
    }

    public boolean setInputFormat(Instances instanceInfo) throws Exception {
        super.setInputFormat(instanceInfo);
        this.m_groupingIndex = -1;
        this.m_currentGroup = -1.0D;

        try {
            this.m_groupingIndex = Integer.parseInt(this.m_groupingAttribute);
            --this.m_groupingIndex;
        } catch (NumberFormatException var4) {
            if (this.m_groupingAttribute.equalsIgnoreCase("first")) {
                this.m_groupingIndex = 0;
            } else if (this.m_groupingAttribute.equalsIgnoreCase("last")) {
                this.m_groupingIndex = instanceInfo.numAttributes() - 1;
            } else {
                for(int i = 0; i < instanceInfo.numAttributes(); ++i) {
                    if (instanceInfo.attribute(i).name().equalsIgnoreCase(this.m_groupingAttribute)) {
                        this.m_groupingIndex = i;
                        break;
                    }
                }
            }
        }

        if (this.m_groupingIndex == -1) {
            throw new Exception("Unable to determine which attribute should be used for grouping!");
        } else if (instanceInfo.attribute(this.m_groupingIndex).isString()) {
            throw new Exception("The grouping attribute must be either numeric or nominal!");
        } else {
            this.createOutputFormat(instanceInfo);
            System.err.println("[Denormalize] WARNING: this filter expects the incoming batch of instances to be sorted in order by the grouping attribute.");
            return true;
        }
    }

    protected boolean convertInstance(Instance instance) throws Exception {
        Instances outputFormat = this.outputFormatPeek();
        boolean instanceOutputted = false;
        int i;
        if (this.m_currentGroup == -1.0D || instance.value(this.m_groupingIndex) != this.m_currentGroup) {
            if (this.m_tempVals != null) {
                for(i = 0; i < outputFormat.numAttributes(); ++i) {
                    if (outputFormat.attribute(i).isNominal()) {
                        if (this.m_nonSparseMarketBasketFormat) {
                            if (this.m_tempVals[i] == -1.0D) {
                                this.m_tempVals[i] = Utils.missingValue();
                            }
                        } else if (this.m_tempVals[i] == -1.0D) {
                            this.m_tempVals[i] = 0.0D;
                        }
                    } else if (outputFormat.attribute(i).isNumeric() && this.m_numericAggregation == DenormalizedCustom.NumericAggregation.AVG && this.m_counts[i] > 0.0D) {
                        this.m_tempVals[i] /= this.m_counts[i];
                    }
                }

                Instance tempI = null;
                tempI = new DenseInstance(1.0D, this.m_tempVals);
                if (this.m_sparseFormat && !this.m_nonSparseMarketBasketFormat) {
                    tempI = new SparseInstance((Instance)tempI);
                }

                this.push((Instance)tempI);
                instanceOutputted = true;
            }

            if (instance != null && (this.m_currentGroup == -1.0D || instance.value(this.m_groupingIndex) != this.m_currentGroup)) {
                this.m_currentGroup = instance.value(this.m_groupingIndex);
                this.m_tempVals = new double[outputFormat.numAttributes()];

                for(i = 0; i < outputFormat.numAttributes(); ++i) {
                    if (outputFormat.attribute(i).isNominal()) {
                        this.m_tempVals[i] = -1.0D;
                    } else if (outputFormat.attribute(i).isNumeric()) {
                        if (this.m_numericAggregation == DenormalizedCustom.NumericAggregation.MAX) {
                            this.m_tempVals[i] = 4.9E-324D;
                        } else if (this.m_numericAggregation == DenormalizedCustom.NumericAggregation.MIN) {
                            this.m_tempVals[i] = 1.7976931348623157E308D;
                        }
                    }
                }

                this.m_counts = new double[outputFormat.numAttributes()];
            }
        }

        if (instance != null) {
            for(i = 0; i < instance.numAttributes(); ++i) {
                if (!Utils.isMissingValue(instance.value(i))) {
                    if (i == this.m_groupingIndex) {
                        int newIndex = ((Integer)this.m_newFormatIndexes.get(instance.attribute(this.m_groupingIndex).name())).intValue();
                        this.m_tempVals[newIndex] = instance.value(this.m_groupingIndex);
                    } else {
                        Integer nn;
                        int newIndex;
                        String newName;
                        if (instance.attribute(i).isNominal()) {
                            newName = instance.attribute(i).name() + "_" + instance.attribute(i).value((int)instance.value(i));
                            nn = (Integer)this.m_newFormatIndexes.get(newName);
                            if (nn != null) {
                                newIndex = nn.intValue();
                                if (this.m_nonSparseMarketBasketFormat) {
                                    this.m_tempVals[newIndex] = 0.0D;
                                } else {
                                    this.m_tempVals[newIndex] = 1.0D;
                                }
                            }
                        } else if (instance.attribute(i).isNumeric()) {
                            newName = this.m_numericAggregation.toString() + "_" + instance.attribute(i).name();
                            nn = (Integer)this.m_newFormatIndexes.get(newName);
                            if (nn != null) {
                                newIndex = nn.intValue();
                                ++this.m_counts[newIndex];
                                switch(this.m_numericAggregation) {
                                    case AVG:
                                    case SUM:
                                        this.m_tempVals[newIndex] += instance.value(i);
                                        break;
                                    case MIN:
                                        if (instance.value(i) < this.m_tempVals[newIndex]) {
                                            this.m_tempVals[newIndex] = instance.value(i);
                                        }
                                        break;
                                    case MAX:
                                        if (instance.value(i) > this.m_tempVals[newIndex]) {
                                            this.m_tempVals[newIndex] = instance.value(i);
                                        }
                                }
                            }
                        }
                    }
                }
            }
        }

        return instanceOutputted;
    }

    public boolean input(Instance instance) throws Exception {
        if (this.getInputFormat() == null) {
            throw new IllegalStateException("No input instance format defined");
        } else {
            if (this.m_NewBatch) {
                this.resetQueue();
                this.m_NewBatch = false;
            }

            return this.convertInstance(instance);
        }
    }

    public boolean batchFinished() throws Exception {
        if (this.getInputFormat() == null) {
            throw new IllegalStateException("No input instance format defined");
        } else {
            if (this.outputFormatPeek() == null) {
                this.createOutputFormat(this.getInputFormat());
            }

            if (this.m_tempVals != null) {
                this.m_currentGroup = -1.0D;
                this.convertInstance((Instance)null);
            }

            this.flushInput();
            this.m_NewBatch = true;
            this.m_currentGroup = -1.0D;
            this.m_tempVals = null;
            this.m_counts = null;
            return this.numPendingOutput() != 0;
        }
    }

    public String groupingAttributeTipText() {
        return "Set the attribute that defines the groups (e.g. transaction ID).";
    }

    public void setGroupingAttribute(String groupAtt) {
        this.m_groupingAttribute = groupAtt;
    }

    public String getGroupingAttribute() {
        return this.m_groupingAttribute;
    }

    public void setUseOldMarketBasketFormat(boolean m) {
        this.m_nonSparseMarketBasketFormat = m;
    }

    public boolean getUseOldMarketBasketFormat() {
        return this.m_nonSparseMarketBasketFormat;
    }

    public String useOldMarketBasketFormatTipText() {
        return "Output instances that contain unary attributes with absence indicated by missing values. Apriori operates faster with this format.";
    }

    public void setUseSparseFormat(boolean s) {
        this.m_sparseFormat = s;
    }

    public boolean getUseSparseFormat() {
        return this.m_sparseFormat;
    }

    public String useSparseFormatTipText() {
        return "Output sparse instances (can't be used in conjunction with useOldMarketBasketFormat).";
    }

    public void setAggregationType(SelectedTag d) {
        int ordinal = d.getSelectedTag().getID();
        DenormalizedCustom.NumericAggregation[] arr$ = DenormalizedCustom.NumericAggregation.values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            DenormalizedCustom.NumericAggregation n = arr$[i$];
            if (n.ordinal() == ordinal) {
                this.m_numericAggregation = n;
                break;
            }
        }

    }

    public SelectedTag getAggregationType() {
        return new SelectedTag(this.m_numericAggregation.ordinal(), TAGS_SELECTION);
    }

    public String aggregationTypeTipText() {
        return "The type of aggregation to apply to numeric attributes.";
    }

    public Enumeration<Option> listOptions() {
        Vector<Option> newVector = new Vector();
        newVector.add(new Option("\tIndex or name of attribute to group by. e.g. transaction ID\n\t(default: first)", "G", 1, "-G <index | name | first | last>"));
        newVector.add(new Option("\tOutput instances in Weka's old market basket format (i.e. unary attributes with absence indicated\n\t by missing values.", "B", 0, "-B"));
        newVector.add(new Option("\tOutput sparse instances (can't be used in conjunction with -B)", "S", 0, "-S"));
        newVector.add(new Option("\tAggregation function for numeric attributes.\n\t(default: sum).", "A", 1, "-A <Average | Sum | Maximum | Minimum>"));
        return newVector.elements();
    }

    public void setOptions(String[] options) throws Exception {
        String groupName = Utils.getOption('G', options);
        if (groupName.length() != 0) {
            this.setGroupingAttribute(groupName);
        }

        this.setUseOldMarketBasketFormat(Utils.getFlag('B', options));
        this.setUseSparseFormat(Utils.getFlag('S', options));
        String aggregation = Utils.getOption('A', options);
        if (aggregation.length() != 0) {
            DenormalizedCustom.NumericAggregation selected = null;
            DenormalizedCustom.NumericAggregation[] arr$ = DenormalizedCustom.NumericAggregation.values();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                DenormalizedCustom.NumericAggregation n = arr$[i$];
                if (n.toString().equalsIgnoreCase(aggregation)) {
                    selected = n;
                    break;
                }
            }

            if (selected == null) {
                throw new Exception("Unknown aggregation type: " + aggregation + "!");
            }

            this.setAggregationType(new SelectedTag(selected.ordinal(), TAGS_SELECTION));
        }

        Utils.checkForRemainingOptions(options);
    }

    public String[] getOptions() {
        ArrayList<String> options = new ArrayList();
        options.add("-G");
        options.add(this.getGroupingAttribute());
        options.add("-A");
        options.add(this.m_numericAggregation.toString());
        if (this.getUseOldMarketBasketFormat()) {
            options.add("-B");
        } else if (this.getUseSparseFormat()) {
            options.add("-S");
        }

        return (String[])options.toArray(new String[1]);
    }

    public String getRevision() {
        return RevisionUtils.extract("$Revision: 10339 $");
    }

    static {
        TAGS_SELECTION = new Tag[]{new Tag(DenormalizedCustom.NumericAggregation.AVG.ordinal(), "Average"), new Tag(DenormalizedCustom.NumericAggregation.SUM.ordinal(), "Sum"), new Tag(DenormalizedCustom.NumericAggregation.MIN.ordinal(), "Minimum"), new Tag(DenormalizedCustom.NumericAggregation.MAX.ordinal(), "Maximum")};
    }

    public static enum NumericAggregation {
        AVG("Average"),
        SUM("Sum"),
        MIN("Minimum"),
        MAX("Maximum");

        private final String m_stringVal;

        private NumericAggregation(String name) {
            this.m_stringVal = name;
        }

        public String toString() {
            return this.m_stringVal;
        }
    }
}
