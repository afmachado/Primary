package com.quaap.primary.math;

import android.content.Context;

import com.quaap.primary.R;
import com.quaap.primary.base.BaseActivity;
import com.quaap.primary.base.InputMode;
import com.quaap.primary.base.Level;

public class BasicMathLevel extends Level {

    private final MathOp mMaxMathOp;
    private final MathOp mMinMathOp;
    private final int mMaxNum;
    private final Negatives mNegatives;
    private InputMode mInputMode;


    public BasicMathLevel(String subjectkey, MathOp maxMathOp, int maxNum, int rounds, InputMode inputMode) {
        this(subjectkey, maxMathOp, maxMathOp, maxNum, rounds, inputMode);
    }
    public BasicMathLevel(String subjectkey, MathOp maxMathOp, MathOp minMathOp, int maxNum, int rounds, InputMode inputMode) {
        this(subjectkey, maxMathOp, minMathOp, maxNum, Negatives.None, rounds, inputMode);
    }
    public BasicMathLevel(String subjectkey, MathOp maxMathOp, int maxNum, Negatives negatives, int rounds, InputMode inputMode) {
        this(subjectkey, maxMathOp, maxMathOp, maxNum, negatives, rounds, inputMode);
    }
    public BasicMathLevel(String subjectkey, MathOp maxMathOp, MathOp minMathOp, int maxNum, Negatives negatives, int rounds, InputMode inputMode) {
        super(subjectkey, rounds);

        mMaxMathOp = maxMathOp;
        mMinMathOp = minMathOp;
        mMaxNum = maxNum;
        mNegatives = negatives;
        mInputMode = inputMode;
    }

    @Override
    public String getDescription(Context context) {
        String ops = getOpsStr(context);
        String max = (mNegatives!=Negatives.None?"\u00B1":"") + mMaxNum;
        return ops + " / " + context.getString(R.string.max, max) + ". " + (mInputMode==InputMode.Buttons ? "Multiple choice" : "Keyboard");

    }

    @Override
    public String getShortDescription(Context context) {
        String ops = getOpsSymStr();
        if (mMaxMathOp == mMinMathOp) {
            ops = mMaxMathOp.name();
        }
        String max = (mNegatives!=Negatives.None?"\u00B1":"") + mMaxNum;
        return context.getString(R.string.max, max) + ". " + ops;
    }

    private String getOpsStr(Context context) {
        String ops = "";
        for (MathOp m: MathOp.values()) {
            if (m.ordinal()>=mMinMathOp.ordinal() && m.ordinal()<=mMaxMathOp.ordinal()) {
                ops += context.getString(m.getResId());
                if (m.ordinal()<mMaxMathOp.ordinal()) {
                    ops += ", ";
                }
            }
        }
        return ops;
    }

    private String getOpsSymStr() {
        String ops = "";
        for (MathOp m: MathOp.values()) {
            if (m.ordinal()>=mMinMathOp.ordinal() && m.ordinal()<=mMaxMathOp.ordinal()) {
                ops += m.toString();
                if (m.ordinal()<mMaxMathOp.ordinal()) {
                    ops += ", ";
                }
            }
        }
        return ops;
    }

    public MathOp getMaxMathOp() {
        return mMaxMathOp;
    }

    public MathOp getMinMathOp() {
        return mMinMathOp;
    }

    public int getMaxNum() {
        return mMaxNum;
    }

    public Negatives getNegatives() { return mNegatives; }

    public InputMode getInputMode() {
        return mInputMode;
    }

//    public int getMaxNum(int prevCorrect) {
//        return (int)(mMaxNum * ((double)Math.max(prevCorrect, mRounds/5.0)/mRounds));
//    }

}
