"use client";

import useGoalsApi from "@/app/hooks/useGoalsAPI";
import { useParams } from "next/navigation";
import TimerDisplay from "@/app/objectives/result/[objectiveId]/TimeDisplay";
import TimerControls from "@/app/objectives/result/[objectiveId]/TimerControls";
import useTimer from "@/app/utils/useTimer";

import "@/app/styles/Timer.scss";
import "./../../../styles/BackgroundImage.scss";
import BackButton from "@/app/components/BackButton";
import useObjectiveApi from "@/app/hooks/useObjectiveApi";
import { useEffect, useState } from "react";

const ObjectiveResult = () => {
    const { objectiveId } = useParams();
    const { createGoal } = useGoalsApi();
    const { getObjectiveDetails, error } = useObjectiveApi();

    const [objectiveDetail, setObjectiveDetail] = useState<string>("");

    const saveGoal = (timeInSeconds: number) => {
        const date = new Date().toISOString().split("T")[0];

        createGoal({
            objectiveId: parseInt(objectiveId as string),
            time: timeInSeconds.toString(),
            date,
        });
    };

    const fetchObjectiveData = async () => {
        if (objectiveId) {
            const objectiveDetails = await getObjectiveDetails({ id: parseInt(objectiveId as string) });
            setObjectiveDetail(objectiveDetails);
        }
    }

    useEffect(() => {
        fetchObjectiveData();
    }, [objectiveId])

    const { time, running, start, stop, reset } = useTimer(saveGoal);

    return (
        <div className="container blur">
            <BackButton />
            <div className="timer-container">
                {error && <p style={{ color: "red" }}>{error}</p>}
                <h1>Résultat {objectiveDetail}</h1>
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
