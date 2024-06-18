<header>
    <div>
    <#if logininfo.role == "ORDINANTE">
        <a href="ordinante"><i class="fas fa-home"></i></a>
    <#elseif logininfo.role == "AMMINISTRATORE">
        <a href="admin"><i class="fas fa-home"></i></a>
    <#elseif logininfo.role == "TECNICO_PREVENTIVI">
        <a href="tecnico_preventivi"><i class="fas fa-home"></i></a>
    <#elseif logininfo.role == "TECNICO_ORDINI">
        <a href="tecnico_ordini"><i class="fas fa-home"></i></a>
    </#if>
    Benvenuto, ${logininfo.email}
    </div>
    <a href="logout">
        <i id="logout" class="fa fa-sign-out" aria-hidden="true"></i>
    </a>
</header>