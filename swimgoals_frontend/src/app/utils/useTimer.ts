// /utils/useTimer.ts

import { useRef, useState } from "react";

const useTimer = (onSave?: (timeInSeconds: number) => void) => {
    const [time, setTime] = useState(0);
    const [running, setRunning] = useState(false);
    const timer = useRef<NodeJS.Timeout | null>(null);

    const start = () => {
        if (timer.current) return;
        setRunning(true);
        timer.current = setInterval(() => {
            setTime((t) => t + 10);
        }, 10);
    };

    const stop = () => {
        if (!timer.current) return;
        clearInterval(timer.current);
        timer.current = null;
        setRunning(false);

        const timeInSeconds = Math.floor(time / 1000);
        onSave?.(timeInSeconds);
    };

    const reset = () => {
        if (timer.current) {
            clearInterval(timer.current);
            timer.current = null;
        }
        setRunning(false);
        setTime(0);
    };

    return { time, running, start, stop, reset };
};

export default useTimer;
