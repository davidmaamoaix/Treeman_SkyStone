package org.firstinspires.ftc.teamcode.subsystem.arm;
import com.qualcomm.robotcore.hardware.HardwareMap;
import  com.qualcomm.robotcore.hardware.Servo;

/*
    This class controls everything related to the arm, including driver assist features.

    IMPORTANT: When working on this class (and arm stuff in general),
    keep the servo names consistent: (from closest to the block to farthest)
        - Claw
        - Wrist
        - Elbow
        - Pivot
 */
public class ArmSystem {
    private Servo claw;
    private Servo wrist;
    private Servo elbow;
    private Servo pivot; // Not yet implemented by build team, ignore until we have it
    protected HardwareMap hardwareMap;
    private final double WRIST_HOME = 0;
    private final double ELBOW_HOME = 0;
    private final double PIVOT_HOME = 0;
    private final double CLAW_OPEN = 0;
    private final double CLAW_CLOSE = 0.5; // I think? TODO: Figure out overheating issueo

    public enum Position {
        POSITION_HOME, POSITION_WEST, POSITION_SOUTH, POSITION_EAST, POSITION_NORTH
    }

    public ArmSystem(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        this.claw = hardwareMap.get(Servo.class, "claw");
        this.wrist = hardwareMap.get(Servo.class, "wrist");
        this.elbow = hardwareMap.get(Servo.class, "elbow");
        this.pivot = hardwareMap.get(Servo.class, "pivot");
    }

    public void moveClaw(double pos) {
        claw.setPosition(pos);
    }

    public void moveWrist(double pos) {
        wrist.setPosition(pos);
    }

    public void moveElbow(double pos) {
        elbow.setPosition(pos);
    }

    public void movePivot(double pos) {
        pivot.setPosition(pos);
    }

    public void increaseClaw(double pos) {
        if (claw.getPosition() + pos <= 1 && claw.getPosition() + pos >= 0) {
            claw.setPosition(claw.getPosition());
        }
    }

    public void increaseWrist(double pos) {
        if (wrist.getPosition() + pos <= 1 && wrist.getPosition() + pos >= 0) {
            wrist.setPosition(wrist.getPosition());
        }
    }

    public void increaseElbow(double pos) {
        if (elbow.getPosition() + pos <= 1 && elbow.getPosition() + pos >= 0) {
            elbow.setPosition(elbow.getPosition());
        }
    }

    public void increasePivot(double pos) {
        if (pivot.getPosition() + pos <= 1 && pivot.getPosition() + pos >= 0) {
            claw.setPosition(claw.getPosition());
        }
    }

    public double getClaw() {
        return claw.getPosition();
    }

    public double getWrist() {
        return wrist.getPosition();
    }

    public double getElbow() {
        return elbow.getPosition();
    }

    public double getPivot() {
        return 0;
    }

    // Moves the arm to the "home state" - the grabber is open, right above the block in the intake.
    // The values of the servos in the home state can be set by editing the final variables.
    public void goHome() {
        openClaw();
        moveWrist(WRIST_HOME);
        moveElbow(ELBOW_HOME);
        movePivot(PIVOT_HOME);
    }

    public void openClaw() {
        moveClaw(CLAW_OPEN);
    }

    public void closeClaw() {
        moveClaw(CLAW_CLOSE);
    }

    public void movePresetPosition(Position pos) {
        switch(pos) {
            case POSITION_HOME:
                openClaw();
                moveWrist(0.6);
                moveElbow(0.35);
                movePivot(0.86);
                break;
            case POSITION_NORTH:
                moveWrist(0.88);
                moveElbow(0.9);
                movePivot(0.1);
                break;
            case POSITION_EAST:
                moveWrist(0.55);
                moveElbow(0.9);
                movePivot(0.1);
                break;
            case POSITION_WEST:
                moveWrist(0.1);
                moveElbow(0.45);
                movePivot(0.1);
                break;
            case POSITION_SOUTH:
                moveWrist(0.55);
                moveElbow(0.45);
                movePivot(0.1);
                break;
        }
    }
}
