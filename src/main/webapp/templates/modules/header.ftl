<header>
    <div>
        <#if logininfo.role == "ORDINANTE">
            <a class="header-a" href="ordinante"><i class="fas fa-home"></i></a>
        <#elseif logininfo.role == "AMMINISTRATORE">
            <a class="header-a" href="admin"><i class="fas fa-home"></i></a>
        <#elseif logininfo.role == "TECNICO_PREVENTIVI">
            <a class="header-a" href="tecnico_preventivi"><i class="fas fa-home"></i></a>
        <#elseif logininfo.role == "TECNICO_ORDINI">
            <a class="header-a" href="tecnico_ordini"><i class="fas fa-home"></i></a>
        </#if>
        <span class="welcome-string">Benvenuto, ${logininfo.email}</span>
    </div>
    <a class="header-a" href="logout">
        <i class="fa fa-sign-out" aria-hidden="true"></i>
    </a>
</header>