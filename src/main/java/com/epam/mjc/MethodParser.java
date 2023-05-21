package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] split = signatureString.split("\\(");
        split[1] = split[1].substring(0, split[1].length() - 1).trim();
        String[] method = split[0].trim().split(" ");
        String methodName;
        String accessModifier = null;
        String returnType;
        if (method.length > 2) {
            accessModifier = method[0];
            returnType = method[1];
            methodName = method[2];
        } else {
            returnType = method[0];
            methodName = method[1];
        }

        String[] methodArgs = split[1].split(",");
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        for (String arg : methodArgs) {
            String[] properties = arg.trim().split(" ");
            if (properties.length > 1) {
                MethodSignature.Argument argument = new MethodSignature.Argument(properties[0], properties[1]);
                arguments.add(argument);
            }
        }

        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);
        return methodSignature;

    }
}
