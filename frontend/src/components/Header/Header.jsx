import './Header.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUpload } from '@fortawesome/free-solid-svg-icons';

function Header({setNav}){
    return(
        <div className="Header">
            <header>
                <h2><FontAwesomeIcon icon={faUpload}/> NF Xml</h2>
                <a href='#' onClick={() => setNav(1)}>Upload</a>
                <a href='#' onClick={() => setNav(2)}>Salvos</a>
            </header>
        </div>
    )
}

export default Header;