package school.hei.patrimoine.cas;

import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.TransfertArgent;
import school.hei.patrimoine.modele.Devise;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Supplier;

import static java.time.Month.*;
import static school.hei.patrimoine.modele.Argent.ariary;

public class PatrimoineBako implements Supplier<Patrimoine> {

    @Override
    public Patrimoine get() {
        var bako = new Personne("Bako");
        var dateActuelle = LocalDate.of(2025, APRIL, 8);
        var dateFinSimulation = LocalDate.of(2025, DECEMBER, 31);

        var compteBNI = new Compte("Compte BNI", dateActuelle, ariary(2_000_000));

        var compteBMOI = new Compte("Compte BMOI", dateActuelle, ariary(625_000));

        var coffreFort = new Compte("Coffre-fort", dateActuelle, ariary(1_750_000));

        var salaire = new FluxArgent(
                "Salaire Bako",
                compteBNI,
                dateActuelle,
                dateFinSimulation,
                2,
                ariary(2_125_000));

        var virementEpargne = new TransfertArgent(
                "Virement épargne BMOI",
                compteBNI,
                compteBMOI,
                dateActuelle,
                dateFinSimulation,
                3,
                ariary(200_000));

        var depensesVie = new FluxArgent(
                "Dépenses de vie",
                compteBNI,
                dateActuelle,
                dateFinSimulation,
                1,
                ariary(-700_000));

        var loyer = new FluxArgent(
                "Loyer colocation",
                compteBNI,
                dateActuelle,
                dateFinSimulation,
                26,
                ariary(-600_000));

        var ordinateur = new Materiel(
                "Ordinateur portable",
                dateActuelle,
                dateActuelle,
                ariary(3_000_000),
                -0.12);

        return Patrimoine.of(
                "Patrimoine de Bako",
                Devise.MGA,
                dateActuelle,
                bako,
                Set.of(
                        compteBNI,
                        compteBMOI,
                        coffreFort,
                        salaire,
                        virementEpargne,
                        depensesVie,
                        loyer,
                        ordinateur
                ));
    }
}