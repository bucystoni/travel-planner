
function formatDuration(totalDurationMinutes) {
    const mins = totalDurationMinutes%60;
    const hours = Math.floor(totalDurationMinutes / 60);
    return `${hours}h ${mins}m`;
}

function formatDepartureArrivalTime(isoString) {
    return new Date(isoString).toLocaleTimeString(undefined, { hour: "2-digit", minute: "2-digit" });
}

function formatPrice(price, currency) {
    return new Intl.NumberFormat(undefined, { style: "currency", currency }).format(price);
}

export {formatDuration, formatDepartureArrivalTime, formatPrice}