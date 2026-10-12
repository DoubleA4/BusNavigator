package id.klacak.bus_navigator;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/** JavaFX row showing a ride leg and its position in an itinerary timeline. */
public class LegRowNode extends HBox {
    /**
     * Creates a row for a ride leg without adjoining-leg context.
     *
     * @param step ride leg represented by this row
     */
    public LegRowNode(Leg step) {
        this(step, 0);
    }

    /**
     * Creates a row and configures its timeline markers for the given position.
     *
     * @param step ride leg represented by this row
     * @param position position marker used to draw the timeline
     */
    public LegRowNode(Leg step, Integer position) {
        this(step, position, null);
    }

    /**
     * Creates a row with timeline context for the preceding ride leg.
     *
     * @param step ride leg represented by this row
     * @param position position marker used to draw the timeline
     * @param previous preceding ride leg, or {@code null} if there is none
     */
    public LegRowNode(Leg step, Integer position, Leg previous) {
        super(12);
        Pane timelinePane = new Pane();
        timelinePane.setPrefWidth(20);

        Circle nodeCircle = new Circle(6);
        nodeCircle.getStyleClass().add("timeline-node");
        nodeCircle.setLayoutX(10);
        nodeCircle.setLayoutY(10);

        Line timelineLine;
        if (position < 0 || position > 1) {
            timelineLine = new Line(10, 10, 10, 65);
        } else {
            //mid
            timelineLine = new Line(10, 10, 10, 70);
        }
        timelineLine.getStyleClass().add("timeline-line");
        timelineLine.setStyle("-fx-stroke: " + step.route().getColor() + ";");
        timelineLine.setStrokeWidth(5);
        timelinePane.getChildren().add(timelineLine);

        if (previous != null) {
            Line prevTimelineLine = new Line(10, 0, 10, 10);
            prevTimelineLine.getStyleClass().add("timeline-line");
            prevTimelineLine.setStyle("-fx-stroke: " + previous.route().getColor() + ";");
            prevTimelineLine.setStrokeWidth(5);

            timelinePane.getChildren().add(prevTimelineLine);
        }

        timelinePane.getChildren().add(nodeCircle);

        if (position < 0 || position > 1) {
            Circle lastNodeCircle = new Circle(6);
            lastNodeCircle.getStyleClass().add("timeline-node");
            lastNodeCircle.setLayoutX(10);
            lastNodeCircle.setLayoutY(65);
            timelinePane.getChildren().add(lastNodeCircle);
        }

        VBox contentBox = new VBox(5);

        Label stopNameLabel = new Label(step.boardStop().getName());
        stopNameLabel.getStyleClass().add("stop-name");
        contentBox.getChildren().add(stopNameLabel);

        HBox badgeBox = new HBox(8);

        Label routeCodeLabel = new Label(step.route().getShortName());
        routeCodeLabel.setAlignment(Pos.CENTER);
        routeCodeLabel.setMinWidth(35);
        if (step.route() instanceof Feeder) {
            routeCodeLabel.getStyleClass().add("feeder-badge");
            routeCodeLabel.setStyle(
                    "-fx-border-color: " + step.route().getColor() + "; " +
                            "-fx-background-color: " + step.route().getTextColor() + "; " +
                            "-fx-text-fill: " + step.route().getColor() + ";"
            );
        } else {
            routeCodeLabel.getStyleClass().add("route-badge");
            routeCodeLabel.setStyle(
                    "-fx-border-color: " + step.route().getColor() + "; " +
                            "-fx-background-color: " + step.route().getColor() + "; " +
                            "-fx-text-fill: " + step.route().getTextColor() + ";"
            );
        }

        Label routeDescLabel = new Label(step.route().getLongName());
        routeDescLabel.getStyleClass().add("route-desc");

        badgeBox.getChildren().addAll(routeCodeLabel, routeDescLabel);

        contentBox.getChildren().add(badgeBox);

        if (position < 0 || position > 1) {
            Label lastStopNameLabel = new Label(step.alightStop().getName());
            lastStopNameLabel.getStyleClass().add("stop-name");
            contentBox.getChildren().add(lastStopNameLabel);
        }

        this.getChildren().addAll(timelinePane, contentBox);
    }
}