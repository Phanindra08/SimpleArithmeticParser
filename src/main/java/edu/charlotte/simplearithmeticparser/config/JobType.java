package edu.charlotte.simplearithmeticparser.config;

import edu.charlotte.simplearithmeticparser.utils.Constants;
import lombok.Getter;

@Getter
public enum JobType {
    SIMPLE_ARITHMETIC_AST_GENERATION(Constants.JOBNAME_SIMPLE_ARITHMETIC_AST_GENERATION, Constants.AST_GENERATION_EXTENSION),
    SIMPLE_ARITHMETIC_PARSE_TREE_GENERATION(Constants.JOBNAME_SIMPLE_ARITHMETIC_PARSE_TREE_GENERATION, Constants.PARSE_TREE_GENERATION_EXTENSION);

    private final String jobNameIdentifier;
    private final String fileExtension;

    JobType(String jobNameIdentifier, String fileExtension) {
        this.jobNameIdentifier = jobNameIdentifier;
        this.fileExtension = fileExtension;
    }

    // Helper method to get JobType from the Job Name identifier string
    public static JobType getJobType(String name) {
        for (JobType type : JobType.values()) {
            if (type.getJobNameIdentifier().equalsIgnoreCase(name))
                return type;
        }
        throw new IllegalArgumentException("Invalid Job: " + name);
    }
}