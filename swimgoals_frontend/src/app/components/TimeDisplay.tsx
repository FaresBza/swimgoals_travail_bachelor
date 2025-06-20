type TimeDisplayProps = {
    time: number;
};

const TimeDisplay = ({ time }: TimeDisplayProps) => {
    const formatTime = (ms: number) => {
        const totalSeconds = Math.floor(ms / 1000);
        const minutes = Math.floor(totalSeconds / 60);
        const seconds = totalSeconds % 60;
        const milliseconds = ms % 1000;

        return `${String(minutes).padStart(2, "0")}:${String(seconds).padStart(2, "0")}:${String(Math.floor(milliseconds / 10)).padStart(2, "0")}`;
    };

    return (
        <h2>{formatTime(time)}</h2>
    );
};

export default TimeDisplay;
