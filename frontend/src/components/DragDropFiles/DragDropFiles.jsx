import { useState, useRef } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUpload, faFile, faCheck, faXmark } from '@fortawesome/free-solid-svg-icons';
import './DragDropFiles.css';

function DragDropFiles({setNav}) {
    const [files, setFiles] = useState(null);
    const [status, setStatus] = useState(null);
    const [dragOver, setDragOver] = useState(false);
    const inputRef = useRef();

    function handleDragOver(e) {
        e.preventDefault();
        setDragOver(true);
    }

    function handleDrop(e) {
        e.preventDefault();
        setFiles(e.dataTransfer.files);
        setDragOver(false);
    }

    function removeFile(value) {
        const filesArray = Array.from(files);
        filesArray.splice(value, 1);

        if (filesArray.length == 0)
            setFiles(null);
        else {
            const newFileList = new DataTransfer();
            filesArray.forEach(file => newFileList.items.add(file));
            setFiles(newFileList.files);
        }
    }

    function handleUpload() {
        if (!files) {
            console.error("Any file selected to upload");
            return;
        }

        const formData = new FormData();
        Array.from(files).forEach((file) => {
            formData.append('files', file);
        })

        fetch('http://localhost:8080/api/invoice/upload', {
            method: 'POST',
            body: formData
        }).then(response => {
            if (!response.ok) {
                throw new Error('Erro ao enviar os arquivos para o servidor');
            }
            return response.json();
        }).then(data => {
            setFiles(null);
            setStatus(data);
        }).catch(error => {
            console.error('Erro:', error);
        });
    }

    if (status) return (
        <div className="Status">
            <h2>Arquivos enviados! ({status.success}/{status.total})</h2>
            <div className="FilesName">
                {status.invoicesStatus.map((invoiceStatus, idx) => <p title={invoiceStatus.error} key={idx}>{!invoiceStatus.success ? <><FontAwesomeIcon icon={faXmark}/> {`(${invoiceStatus.error})`} </>: <FontAwesomeIcon icon={faCheck}/>} {invoiceStatus.fileName}</p>)}
            </div>
            <div className="Options">
                <button onClick={() => setNav(2)}>Ver uploads</button>
                <button onClick={() => setStatus(null)}>Enviar novamente</button>
            </div>
        </div>
    )

    if (files) return (
        <div className="FilesUploads">
            <div className="FilesName">
                {Array.from(files).map((file, idx) => <p key={idx} onClick={() => { removeFile(idx) }}><FontAwesomeIcon icon={faFile} /> {file.name}</p>)}
            </div>
            <div className="Options">
                <button onClick={() => setFiles(null)}>Cancelar</button>
                <button onClick={handleUpload}>Enviar</button>
            </div>
        </div>
    )

    return (
        <div className={`DragDropFiles ${dragOver ? 'DragOver' : ''}`} onDragOver={handleDragOver} onDrop={handleDrop}>
            {!files && (
                <div>
                    <h1><FontAwesomeIcon icon={faUpload} /></h1>
                    <h1>{!dragOver ? 'Arraste arquivos aqui para fazer o Upload' : 'Solte os arquivos aqui para fazer o Upload'}</h1>
                    <h2>Ou</h2>
                    <input type="file" multiple onChange={(e) => setFiles(e.target.files)} hidden ref={inputRef} />
                    <button onClick={() => inputRef.current.click()}>Select Files</button>
                </div>
            )}
        </div>
    )
}

export default DragDropFiles;