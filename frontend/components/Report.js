import { useState } from 'react';
import Button from './Button';
import { generateReport } from '../utils/report';

function Report({ chargingPoints }) {
    const [reportOpen, setReportOpen] = useState(false);

    return (
        <div>
            <Button onClick={() => setReportOpen(!reportOpen)}>
                {reportOpen ? `Close` : 'View'} Report
            </Button>
            {reportOpen && (
                <pre className="report">
                    {generateReport(chargingPoints)}
                </pre>
            )}
            <style jsx>{`
                div {
                    float: right;
                    text-transform: none;
                    font-size: 12px;
                    position: relative;
                }
                .report {
                    position: absolute;
                    background: rgba(0, 0, 0, 0.8);
                    padding: 15px;
                    font-family: monospace;
                    font-size: 16px;
                    z-index: 1;
                    right: 10px;
                }
            `}</style>
        </div>
    );
}

export default Report;