<header>
    <div>
    <#if logininfo.role == "ORDINANTE">
        <a class="header-a" href="ordinante"><i class="fas fa-home"></i></a>
    <#elseif logininfo.role == "AMMINISTRATORE">
        <a class="head-a" href="admin"><i class="fas fa-home"></i></a>
    <#elseif logininfo.role == "TECNICO_PREVENTIVI">
        <a class="head-a" href="tecnico_preventivi"><i class="fas fa-home"></i></a>
    <#elseif logininfo.role == "TECNICO_ORDINI">
        <a class="head-a" href="tecnico_ordini"><i class="fas fa-home"></i></a>
    </#if>
    Benvenuto, ${logininfo.email}
    </div>
    <a class="header-a" href="logout">
        <i class="fa fa-sign-out" aria-hidden="true"></i>
    </a>
</header>