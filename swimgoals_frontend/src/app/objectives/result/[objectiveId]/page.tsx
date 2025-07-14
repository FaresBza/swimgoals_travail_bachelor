"use client";

import useGoalsApi from "@/app/hooks/useGoalsAPI";
import { useParams } from "next/navigation";
import TimerDisplay from "@/app/objectives/result/[objectiveId]/TimeDisplay";
import TimerControls from "@/app/objectives/result/[objectiveId]/TimerControls";
import useTimer from "@/app/utils/useTimer";

import "@/app/styles/Timer.scss";
import "./../../../styles/BackgroundImage.scss";

const ObjectiveResult = () => {
    const { objectiveId } = useParams();
    const { createGoal } = useGoalsApi();

    const saveGoal = (timeInSeconds: number) => {
        const date = new Date().toISOString().split("T")[0];

        createGoal({
            objectiveId: parseInt(objectiveId as string),
            time: timeInSeconds.toString(),
            date,
        });
    };

    const { time, running, start, stop, reset } = useTimer(saveGoal);

    return (
        <div className="container blur">
            <div className="timer-container">
                <h1>Résultat de l’objectif #{objectiveId}</h1>
                <TimerDisplay time={time} />
                <TimerControls
                    isRunning={running}
                    onStart={start}
                    onStop={stop}
                    onReset={reset}
                />
            </div>
        </div>
    );
};

export default ObjectiveResult;
