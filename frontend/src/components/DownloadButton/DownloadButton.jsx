import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faDownload } from '@fortawesome/free-solid-svg-icons';
import './DownloadButton.css';

function DownloadButton({binary}){
    function downloadXML(){
        const xmlString = atob(binary);
        const blob = new Blob([xmlString], {type: 'text/xml'});
        const url = URL.createObjectURL(blob);
        const link = document.createElement('a');
        link.href = url;
        link.download = 'NF.xml';
        link.click();
        URL.revokeObjectURL(url);    
    }

    return(
        <div className="DownloadButton">
            <a href="#" onClick={downloadXML}><FontAwesomeIcon icon={faDownload}/></a>
        </div>
    )   
}

export default DownloadButton;