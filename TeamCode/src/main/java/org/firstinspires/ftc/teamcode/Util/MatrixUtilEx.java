package org.firstinspires.ftc.teamcode.Util;

import com.pedropathing.math.Matrix;
import com.pedropathing.math.MatrixUtil;

public class MatrixUtilEx extends MatrixUtil {
    public static Matrix identity(int dim){
        Matrix output = new Matrix(dim, dim);
        for (int i = 0; i < dim; i++) {
            output.set(i, i, 1);
        }
        return output;
    }
}
