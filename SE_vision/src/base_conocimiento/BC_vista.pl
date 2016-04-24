%A
%B
%C
es_sintoma(cansansio_trabajo). %listo cansancio ojos en el trabajo
%D
es_sintoma(dolor_cabeza).%listo
es_sintoma(dolor_ocular).%listo
es_sintoma(d_letra_chica). %listo dificultad para ver letra chica
es_sintoma(d_objeto_cerca).%listo dificultad ver objetos cercanos
es_sintoma(d_objeto_lejo). %listo dificultad ver objetos lejanos
es_sintoma(d_conducir_noche). %listo dificultad para conducir de noche
%E
es_sintoma(entrecerrar_ojos).%listo
%F
es_sintoma(fatiga_visual).%listo
%G
%H
%I
es_sintoma(irritacion).%listo  despues de forzar vista
%L
es_sintoma(luz_deslumbrante).%listo
%M
es_sintoma(mayor_distancia).%listo ajelar objetos
%N
%O
es_sintoma(ojos_bizcos).%listo
es_sintoma(ojos_enrojecidos).%listo
es_sintoma(ojos_hinchados).%listo
es_sintoma(ojos_cansados).%listo
%P
%Q
%R
%S
%T
es_sintoma(tension_ojos).%listo aumento tension ocular
%U
%V
es_sintoma(vision_doble).%listo
es_sintoma(vision_nublada).%listo
es_sintoma(vision_borrosa).%listo
es_sintoma(vision_distorcionada).%listo
%W
%X
%Y
%Z
%%---MIOPIA---%%
sintoma_de(dolor_cabeza     , miopia).
sintoma_de(entrecerrar_ojos , miopia).
sintoma_de(fatiga_visual    , miopia).
sintoma_de(d_objeto_lejo    , miopia).
sintoma_de(tension_ojos     , miopia).
sintoma_de(irritacion       , miopia).
sintoma_de(cansansio_trabajo, miopia).
%%---HIPERMETROPIA---%%
sintoma_de(dolor_cabeza    , hipermetropia).
sintoma_de(fatiga_visual   , hipermetropia).
sintoma_de(entrecerrar_ojos, hipermetropia).
sintoma_de(vision_borrosa  , hipermetropia).
sintoma_de(dolor_ocular    , hipermetropia).
sintoma_de(ojos_bizcos     , hipermetropia).
%%---ASTIGMATISMO---%%
sintoma_de(dolor_cabeza        , astigmatismo).
sintoma_de(fatiga_visual       , astigmatismo).
sintoma_de(entrecerrar_ojos    , astigmatismo).
sintoma_de(vision_borrosa      , astigmatismo).
sintoma_de(vision_distorcionada, astigmatismo).
sintoma_de(d_conducir_noche    , astigmatismo).
sintoma_de(ojos_enrojecidos    , astigmatismo).
sintoma_de(ojos_hinchados      , astigmatismo).
sintoma_de(ojos_cansados       , astigmatismo).
%%---PRESBICIA---%%
sintoma_de(d_letra_chica  , presbicia).
sintoma_de(mayor_distancia, presbicia).
sintoma_de(d_objeto_cerca , presbicia).
sintoma_de(dolor_cabeza   , presbicia).
sintoma_de(fatiga_visual  , presbicia).

problema_de(X,Y) :- es_sintoma(X), sintoma_de(X,Y).



