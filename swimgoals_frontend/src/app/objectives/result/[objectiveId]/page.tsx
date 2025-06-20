"use client"

import useGoalsApi from "@/app/hooks/useGoalsAPI";
import { useParams } from "next/navigation";
import { useState, useRef } from "react";
import TimerDisplay from "@/app/components/TimeDisplay";
import TimerControls from "@/app/components/TimerControls";

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

    return (
        <div style={{ textAlign: "center", marginTop: 50 }}>
        <h1>Résultat de l’objectif #{objectiveId}</h1>
        <TimerDisplay time={time} />
        <TimerControls isRunning={running} onStart={start} onStop={stop} />
        </div>
    );
};

export default ObjectiveResult;
