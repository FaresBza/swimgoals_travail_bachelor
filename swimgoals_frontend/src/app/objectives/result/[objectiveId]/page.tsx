"use client"

import useGoalsApi from "@/app/hooks/useGoalsAPI";
import { useParams } from "next/navigation";
import { useState, useRef } from "react";
import TimerDisplay from "@/app/objectives/result/[objectiveId]/TimeDisplay";
import TimerControls from "@/app/objectives/result/[objectiveId]/TimerControls";

import '@/app/styles/Timer.scss'
import "./../../../styles/BackgroundImage.scss";

const ObjectiveResult = () => {
    const { objectiveId } = useParams();
    const { createGoal } = useGoalsApi();

    const [time, setTime] = useState(0);
    const [running, setRunning] = useState(false);
    const timer = useRef<NodeJS.Timeout | null>(null);

    const start = () => {
        if (timer.current) return;
        setRunning(true);
        timer.current = setInterval(() => setTime((t) => t + 10), 10);
    };

    const stop = () => {
        if (!timer.current) return;
        clearInterval(timer.current);
        timer.current = null;
        setRunning(false);

        // format de la date : "YYYY-MM-DD"
        const date = new Date().toISOString().split("T")[0];
        const timeInSeconds = Math.floor(time / 1000).toString();

        createGoal({
        objectiveId: parseInt(objectiveId as string),
        time: timeInSeconds,
        date,
        });
    };

    const resetTimer = () => {
        if (timer.current) {
            clearInterval(timer.current);
            timer.current = null;
        }
        setRunning(false);
        setTime(0);
    }

    return (
        <div className="container blur">
            <div className="timer-container">
                <h1>Résultat de l’objectif #{objectiveId}</h1>
                <TimerDisplay time={time} />
                <TimerControls isRunning={running} onStart={start} onStop={stop} onReset={resetTimer} />
            </div>
        </div>
    );

};

export default ObjectiveResult;
