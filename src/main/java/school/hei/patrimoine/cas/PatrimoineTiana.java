package school.hei.patrimoine.cas;

import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Argent;
import school.hei.patrimoine.modele.possession.Dette;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Supplier;

import static java.time.Month.*;

public class PatrimoineTiana implements Supplier<Patrimoine> {

    @Override
    public Patrimoine get() {
        var tiana = new Personne("Tiana");
        var dateActuelle = LocalDate.of(2025, APRIL, 8);
        var dateFinSimulation = LocalDate.of(2026, MARCH, 31);

        var compteBancaire = new Argent("Compte bancaire", dateActuelle, 60_000_000);

        var terrainBati = new Materiel(
                "Terrain bâti",
                dateActuelle,
                100_000_000,
                dateActuelle,
                0.10);

        var depensesFamiliales = new FluxArgent(
                "Dépenses familiales",
                compteBancaire,
                dateActuelle,
                dateFinSimulation,
                -4_000_000,
                1);

        var debutProjet = LocalDate.of(2025, JUNE, 1);
        var finProjet = LocalDate.of(2025, DECEMBER, 31);

        var depensesProjet = new FluxArgent(
                "Dépenses projet",
                compteBancaire,
                debutProjet,
                finProjet,
                -5_000_000,
                5);

        var revenuInitialProjet = new FluxArgent(
                "Revenu initial projet (10%)",
                compteBancaire,
                LocalDate.of(2025, MAY, 1),
                LocalDate.of(2025, MAY, 1),
                7_000_000,
                1);

        var revenuFinalProjet = new FluxArgent(
                "Revenu final projet (90%)",
                compteBancaire,
                LocalDate.of(2026, JANUARY, 31),
                LocalDate.of(2026, JANUARY, 31),
                63_000_000,
                31);

        var detteBancaire = new Dette("Prêt bancaire", dateActuelle, 0);

        var pretRecu = new FluxArgent(
                "Prêt bancaire reçu",
                compteBancaire,
                LocalDate.of(2025, JULY, 27),
                LocalDate.of(2025, JULY, 27),
                20_000_000,
                27);

        var creationDette = new FluxArgent(
                "Création dette bancaire",
                detteBancaire,
                LocalDate.of(2025, JULY, 27),
                LocalDate.of(2025, JULY, 27),
                -20_000_000,
                27);

        var remboursementPret = new FluxArgent(
                "Remboursement prêt",
                compteBancaire,
                LocalDate.of(2025, AUGUST, 27),
                LocalDate.of(2026, JULY, 27),
                -2_000_000,
                27);

        var reductionDette = new FluxArgent(
                "Réduction dette bancaire",
                detteBancaire,
                LocalDate.of(2025, AUGUST, 27),
                LocalDate.of(2026, JULY, 27),
                2_000_000,
                27);

        return new Patrimoine(
                "Patrimoine de Tiana",
                tiana,
                dateActuelle,
                Set.of(
                        compteBancaire,
                        terrainBati,
                        depensesFamiliales,
                        depensesProjet,
                        revenuInitialProjet,
                        revenuFinalProjet,
                        detteBancaire,
                        pretRecu,
                        creationDette,
                        remboursementPret,
                        reductionDette
                ));
    }
}