import { ObjectiveCardData } from "./ObjectiveCardData";

export interface ObjectiveCardProps {
    objective: ObjectiveCardData;
    roleId: number;
    goToAddResultPage: (objectiveId: number) => void;
    goToOneObjectivePerformance: (objectiveId: number) => void;
}