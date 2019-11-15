using UnityEngine;
using UnityEngine.SceneManagement;

public class GameManager : MonoBehaviour
{
    bool gameHasEnded = false;
    public float restartDelay = 1.0f;
    public GameObject completeLevelUI;
    public void endGame()
    {
        // Toggle flag variable
        if (!gameHasEnded)
        {
            gameHasEnded = true;
            Debug.Log("Game Over");
            Invoke("restart", restartDelay);
        }

    }

    void restart()
    {

        SceneManager.LoadScene(SceneManager.GetActiveScene().name);

    }

    public void completeLevel() 
    {
        completeLevelUI.SetActive(true);
    }
}

