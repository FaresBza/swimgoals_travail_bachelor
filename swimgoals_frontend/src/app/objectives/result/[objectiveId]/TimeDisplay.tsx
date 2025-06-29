import "@/app/styles/Timer.scss";

const TimeDisplay = ({ time }: { time: number }) => {
    const formatTime = (ms: number) => {
        const totalSeconds = Math.floor(ms / 1000);
        const minutes = Math.floor(totalSeconds / 60);
        const seconds = totalSeconds % 60;
        const milliseconds = ms % 1000;

        return `${String(minutes).padStart(2, "0")}:${String(seconds).padStart(2, "0")}:${String(Math.floor(milliseconds / 10)).padStart(2, "0")}`;
    };

    return <div className="timer-display">{formatTime(time)}</div>;
};

export default TimeDisplay;
