export function generateReport(chargingPoints) {
    return chargingPoints.map(({ id, amps, occupied }) => {
        let line = `CP${id} ${occupied ? 'OCCUPIED' : 'AVAILABLE'}`;
        if (occupied) {
            line += ` ${amps}A`;
        }
        return line;
    }).join('\n');
}